package br.com.fiap.navegacaoentretelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.fiap.navegacaoentretelas.screens.LoginScreen
import br.com.fiap.navegacaoentretelas.screens.MenuScren
import br.com.fiap.navegacaoentretelas.screens.PedidosScreen
import br.com.fiap.navegacaoentretelas.screens.PerfilScreen
import br.com.fiap.navegacaoentretelas.ui.theme.NavegacaoEntreTelasTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoEntreTelasTheme {
                // função de estado que vai lembrar por todas as telas que passamos
                val navController = rememberAnimatedNavController()
                // navController é responsável pelas rotas, startDestination tela que será aberta
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "login",
                    //aplicando os efeitos visuais de entrada e saída
                    exitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            tween(2000)
                        ) + fadeOut(animationSpec = tween(2000))
                    }, enterTransition = {
                        slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            tween(2000)
                        )
                    }
                ) {
                    //composable recebe uma rota
                    composable(route = "login") {
                        LoginScreen(navController)
                    }
                    composable(route = "menu") {
                        MenuScren(navController)
                    }
                    composable(
                        route = "perfil/{nome}/{idade}",
                        arguments = listOf(
                            navArgument(name = "nome") {
                                type = NavType.StringType
                            },
                            navArgument(name = "idade") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val nome = it.arguments?.getString("nome")
                        val idade = it.arguments?.getInt("idade")
                        PerfilScreen(navController, nome!!, idade!!)
                    }
                    composable(
                        route = "pedidos?numeroPedido={numeroPedido}",
                        arguments = listOf(navArgument(name = "numeroPedido") {
                            defaultValue = "número não informado"
                        })
                    ) {
                        PedidosScreen(navController, it.arguments?.getString("numeroPedido")!!)
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun NavegacaoEntreTelasThemePreview() {
//    NavegacaoEntreTelasTheme()
//}