package br.com.fiap.navegacaoentretelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            NavegacaoEntreTelasTheme() {
                // função de estado que vai lembrar por todas as telas que passamos
                val navController = rememberNavController()
                // navController é responsável pelas rotas, startDestination tela que será aberta
                NavHost(navController = navController, startDestination = "login") {
                    //composable recebe uma rota
                    composable(route = "login"){
                        LoginScreen(navController)
                    }
                    composable(route = "menu"){
                        MenuScren(navController)
                    }
                    composable(route = "perfil"){
                        PerfilScreen(navController)
                    }
                    composable(route = "pedidos"){
                        PedidosScreen(navController)
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