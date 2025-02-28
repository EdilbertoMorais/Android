package br.com.fiap.calculadoradejurossimples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.calculadoradejurossimples.juros.JurosScreen
import br.com.fiap.calculadoradejurossimples.juros.JurosScreenViewModel
import br.com.fiap.calculadoradejurossimples.ui.theme.CalculadoraDeJurosSimplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraDeJurosSimplesTheme {
                JurosScreen(JurosScreenViewModel())
            }
        }
    }
}

@Preview
@Composable
fun JurosScreenPreview() {
    JurosScreen(JurosScreenViewModel())
}