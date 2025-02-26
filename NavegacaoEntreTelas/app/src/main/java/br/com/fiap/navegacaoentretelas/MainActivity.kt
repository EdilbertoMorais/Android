package br.com.fiap.navegacaoentretelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.navegacaoentretelas.screens.LoginScreen
import br.com.fiap.navegacaoentretelas.screens.MenuScren
import br.com.fiap.navegacaoentretelas.screens.PedidosScreen
import br.com.fiap.navegacaoentretelas.screens.PerfilScreen
import br.com.fiap.navegacaoentretelas.ui.theme.NavegacaoEntreTelasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoEntreTelasTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login",
                    enterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(1500)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(1500)
                        ) + fadeOut(animationSpec = tween(1500))
                    }
                ) {
                    composable(route = "login") {
                        LoginScreen(navController)
                    }
                    composable(route = "menu") {
                        MenuScren(navController)
                    }
                    composable(
                        route = "perfil/{nome}/{idade}",
                        arguments = listOf(
                            navArgument("nome") { type = NavType.StringType },
                            navArgument("idade") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val nome = backStackEntry.arguments?.getString("nome") ?: ""
                        val idade = backStackEntry.arguments?.getInt("idade") ?: 0
                        PerfilScreen(navController, nome, idade)
                    }
                    composable(
                        route = "pedidos?numeroPedido={numeroPedido}",
                        arguments = listOf(
                            navArgument("numeroPedido") { defaultValue = "número não informado" }
                        )
                    ) { backStackEntry ->
                        val numeroPedido = backStackEntry.arguments?.getString("numeroPedido")
                            ?: "número não informado"
                        PedidosScreen(navController, numeroPedido)
                    }
                }
            }
        }
    }
}