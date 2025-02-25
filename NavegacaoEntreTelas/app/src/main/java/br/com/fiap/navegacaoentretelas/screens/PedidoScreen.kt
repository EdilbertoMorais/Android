package br.com.fiap.navegacaoentretelas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PedidosScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(32.dp)
    ){
        Text(
            text = "PEDIDOS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB3FF04)
        )
        Button(
            onClick = {
                navController.navigate("menu")
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFB3FF04)),
            modifier = Modifier
                .align(Alignment.Center)
                .size(
                    width = 200.dp,
                    height = 48.dp
                )
        ) {
            Text(text = "Voltar", fontSize = 20.sp, color = Color.Black)
        }
    }
}

//@Preview
//@Composable
//fun PedidosScreenPreview() {
//    PedidosScreen()
//}