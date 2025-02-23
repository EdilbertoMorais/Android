package br.com.fiap.navegacaoentretelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.navegacaoentretelas.ui.theme.NavegacaoEntreTelasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoEntreTelasTheme() {

            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun NavegacaoEntreTelasThemePreview() {
//    NavegacaoEntreTelasTheme()
//}