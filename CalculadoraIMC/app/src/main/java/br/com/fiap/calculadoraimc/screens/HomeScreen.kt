package br.com.fiap.calculadoraimc.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.calculadoraimc.R
import br.com.fiap.calculadoraimc.calcularIMC
import br.com.fiap.calculadoraimc.obterStatusIMC
import br.com.fiap.calculadoraimc.ui.theme.shadowsIntoLightRegular

@Composable
fun IMCScreen() {

    var corBoxResultado by remember {
        mutableStateOf(Color(0xFFB3FF04))
    }

    var corTextoResultado by remember {
        mutableStateOf(Color.Black)
    }

    var peso by remember {
        mutableStateOf("")
    }
    var altura by remember {
        mutableStateOf("")
    }

    var imc by remember {
        mutableStateOf(0.0)
    }

    var statusIMC by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //--------- header ---------
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.balanca),
                    contentDescription = "imagem de um notebook",
                    modifier = Modifier
                )

            }
            // --------- formulário ---------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "Calculadora IMC",
                    textAlign = TextAlign.Center,
                    fontFamily = shadowsIntoLightRegular,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB3FF04),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally)
                        .offset(y = (-20).dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(32.dp)),
                    )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-25).dp),

                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    border = BorderStroke(width = 2.dp, color = Color(0xFFB3FF04)),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 32.dp)
                    ) {
                        Text(
                            text = "Seus Dados",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.verde_atypical),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        Text(
                            text = "Seu peso",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 16.sp,
                            color = colorResource(R.color.verde_atypical),
                            fontWeight = FontWeight.Light
                        )
                        OutlinedTextField(
                            value = peso,
                            onValueChange = {
                                peso = it
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "Digite seu peso Ex: 99",
                                    color = colorResource(id = R.color.verde_atypical)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.verde_atypical),
                                focusedBorderColor = colorResource(id = R.color.verde_atypical),
                                cursorColor = colorResource(id = R.color.verde_atypical)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(
//                                color = colorResource(id = R.color.verde_atypical),
                                color = colorResource(id = R.color.teal_200),
                            )
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        Text(
                            text = "Sua altura",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 16.sp,
                            color = colorResource(R.color.verde_atypical),
                            fontWeight = FontWeight.Light
                        )
                        OutlinedTextField(
                            value = altura,
                            onValueChange = {
                                altura = it
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "Digite sua altura Ex: 999",
                                    color = colorResource(id = R.color.verde_atypical)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.verde_atypical),
                                focusedBorderColor = colorResource(id = R.color.verde_atypical),
                                cursorColor = colorResource(id = R.color.verde_atypical)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(
                                color = colorResource(id = R.color.verde_atypical),
                            )
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        Button(
                            onClick = {
                                imc =
                                    calcularIMC(
                                        pesoUsuario = peso.toDouble(),
                                        alturaUsuario = altura.toDouble()
                                    )
                                statusIMC = obterStatusIMC(imcUsuario = imc)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.verde_atypical)
                            )
                        ) {
                            Text(
                                text = "Calcular IMC",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
        // --------- Card Resultado ---------
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 20.dp),
            colors = CardDefaults.run { cardColors(containerColor = corBoxResultado) },
            elevation = CardDefaults.cardElevation(4.dp),
            border = BorderStroke(width = 1.dp, colorResource(R.color.verde_atypical))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxSize()
            ) {
                Column() {
                    Text(
                        text = "Resultado:",
                        color = corTextoResultado,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = statusIMC,
                        fontWeight = FontWeight.Bold,
                        color = corTextoResultado,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = String.format("%.2f", imc),
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = corTextoResultado,
                    fontSize = 36.sp,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Preview
@Composable
fun IMCScreenPreview() {
    IMCScreen()
}