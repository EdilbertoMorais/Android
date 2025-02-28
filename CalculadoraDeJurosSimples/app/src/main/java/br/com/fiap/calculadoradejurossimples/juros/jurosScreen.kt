package br.com.fiap.calculadoradejurossimples.juros

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.calculadoradejurossimples.R
import br.com.fiap.calculadoradejurossimples.calculos.calcularJuros
import br.com.fiap.calculadoradejurossimples.calculos.calcularMontante
import br.com.fiap.calculadoradejurossimples.componentes.CaixaDeEntrada
import br.com.fiap.calculadoradejurossimples.componentes.CardResultado

@Composable
fun JurosScreen(jurosScreenViewModel: JurosScreenViewModel) {
//    var capital by remember { mutableStateOf("") }
    val capital by jurosScreenViewModel.capital.observeAsState(initial = "")

//    var taxa by remember { mutableStateOf("") }
    val taxa by jurosScreenViewModel.taxa.observeAsState(initial = "")

//    var tempo by remember { mutableStateOf("") }
    val tempo by jurosScreenViewModel.tempo.observeAsState(initial = "")

//    var juros by remember { mutableDoubleStateOf(0.0) }
    val juros by jurosScreenViewModel.juros.observeAsState(initial = 0.0)

//    var montante by remember { mutableDoubleStateOf(0.0) }
    val montante by jurosScreenViewModel.montante.observeAsState(initial = 0.0)

    Box(
        modifier = Modifier
            .background(Color(0xFF282828))
            .padding(top = 25.dp, start = 2.dp, end = 2.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
//                .background(Color.Black)
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(R.drawable.calculadora_normal),
                contentDescription = "Imagem de uma calculadora",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Text(
                text = "Cálculo de Juros Simples",
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-30).dp)
                    .padding(end = 10.dp),
                fontSize = 20.sp,
                color = Color(0xFF000000),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
            )
//            Spacer(modifier = Modifier.height(6.dp))
            //Formulário para entrada de dados
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp)
                    .padding(horizontal = 5.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color(0xFF393B3B))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Dados do Investimento",
                        color = Color(0xFFFFD600),
                        fontWeight = FontWeight.Bold
                    )
                    CaixaDeEntrada(
                        label = "Valor do Investimento",
                        labelColor = Color(0xFFFFD600),
                        placeHolder = "Quanto deseja investir?",
                        placeHolderColor = Color(0xFFFFD600),
                        value = capital,
                        textStyleColor = Color(0xFFFFD600),
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        atualizarValor = {
                            jurosScreenViewModel.onCapitalChanged(it)
                        },
                        singleLine = true,
                        unfocusedBorderColor = Color(0xFFFFD600),
                        focusedBorderColor = Color(0xFFFFD600)
                    )
                    CaixaDeEntrada(
                        label = "Taxa de juros mensal",
                        labelColor = Color(0xFFFFD600),
                        placeHolder = "Qual a taxa de juros mensal?",
                        placeHolderColor = Color(0xFFFFD600),
                        value = taxa,
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        atualizarValor = {
                            jurosScreenViewModel.onTaxaChanged(it)
                        },
                        singleLine = true,
                        focusedBorderColor = Color(0xFFFFD600),
                        unfocusedBorderColor = Color(0xFFFFD600),
                        textStyleColor = Color(0xFFFFD600),
                    )
                    CaixaDeEntrada(
                        label = "Periodo em meses",
                        labelColor = Color(0xFFFFD600),
                        placeHolder = "Qual o periodo em meses?",
                        placeHolderColor = Color(0xFFFFD600),
                        value = tempo,
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        atualizarValor = {
                            jurosScreenViewModel.onTempoChanged(it)
                        },
                        singleLine = true,
                        focusedBorderColor = Color(0xFFFFD600),
                        unfocusedBorderColor = Color(0xFFFFD600),
                        textStyleColor = Color(0xFFFFD600),
                    )

                    Button(
                        onClick = {
                            jurosScreenViewModel.calcularJurosViewModel()
                            jurosScreenViewModel.calcularMontanteViewModel()
                        },
                        modifier = Modifier
                            .width(250.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFD600))
                    ) {
                        Text(text = "CALCULAR", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }
            }
            CardResultado(juros, montante)
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun JurosScreenPrev() {
    JurosScreen(JurosScreenViewModel())
}