package br.com.fiap.consultacep

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.consultacep.model.Endereco
import br.com.fiap.consultacep.service.RetrofitFactory
import br.com.fiap.consultacep.ui.theme.ConsultaCEPTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        setContent {
            ConsultaCEPTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    CepScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CepScreen(modifier: Modifier = Modifier) {

    var cepState by remember { mutableStateOf("") }
    var ufState by remember { mutableStateOf("") }
    var cidadeState by remember { mutableStateOf("") }
    var ruaState by remember { mutableStateOf("") }
    var listaCepStates by remember { mutableStateOf(listOf<Endereco>()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                ),
            colors = CardDefaults.cardColors(Color(0xFF7D5BEA))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Encontre seu CEP",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Encontre o seu endereço",
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = cepState,
                    onValueChange = {
                        cepState = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Qual o CEP procurado?", color = Color.White)
                    },
                    placeholder = {
                        Text(text = "Digite o CEP", color = Color(0xFF4A148C))
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            var call = RetrofitFactory().getCepService().getEnderecoByCep(
                                cep = cepState
                            )
                            call.enqueue(object : Callback<Endereco> {
                                override fun onResponse(
                                    call: Call<Endereco>,
                                    response: Response<Endereco>
                                ) {
                                    listaCepStates = listOf(response.body()!!)
                                }

                                override fun onFailure(
                                    call: Call<Endereco?>,
                                    t: Throwable
                                ) {
                                    Log.i("FIAP", "onResponse: ${t.message}")
                                }
                            })


                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Icone de Pesquisa",
                                tint = Color(0xFF4A148C)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xFFFFFFFF),
                        unfocusedTextColor = Color(0xFFFFFFFF),
                        focusedContainerColor = Color(0xFFAB92F8),
                        unfocusedContainerColor = Color(0xFFAB92F8),
                        focusedIndicatorColor = Color(0xFFF4F2F8),
                        unfocusedIndicatorColor = Color(0xFFF4F2F8),
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Não sabe o CEP ?",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Preencha os campos abaixo:",
                    color = Color.White,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    OutlinedTextField(
                        value = ufState,
                        onValueChange = {
                            ufState = it
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp),
                        label = {
                            Text(text = "UF?", color = Color.White)
                        },
                        placeholder = {
                            Text(text = "Digite a UF", color = Color(0xFF4A148C))
                        },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters,
                            keyboardType = KeyboardType.Text,
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFFFFFFFF),
                            unfocusedTextColor = Color(0xFFFFFFFF),
                            focusedContainerColor = Color(0xFFAB92F8),
                            unfocusedContainerColor = Color(0xFFAB92F8),
                            focusedIndicatorColor = Color(0xFFF4F2F8),
                            unfocusedIndicatorColor = Color(0xFFF4F2F8),
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = cidadeState,
                        onValueChange = {
                            cidadeState = it
                        },
                        modifier = Modifier.weight(2f),
                        label = {
                            Text(text = "Qual a cidade?", color = Color.White)
                        },
                        placeholder = {
                            Text(text = "Digite sua Cidade", color = Color(0xFF4A148C))
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFFFFFFFF),
                            unfocusedTextColor = Color(0xFFFFFFFF),
                            focusedContainerColor = Color(0xFFAB92F8),
                            unfocusedContainerColor = Color(0xFFAB92F8),
                            focusedIndicatorColor = Color(0xFFF4F2F8),
                            unfocusedIndicatorColor = Color(0xFFF4F2F8),
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = ruaState,
                        onValueChange = {
                            ruaState = it
                        },
                        modifier = Modifier
                            .weight(2f),
                        label = {
                            Text(text = "Digite o nome da rua:", color = Color.White)
                        },
                        placeholder = {
                            Text(text = "Digite o nome da rua", color = Color(0xFF4A148C))
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words,
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFFFFFFFF),
                            unfocusedTextColor = Color(0xFFFFFFFF),
                            focusedContainerColor = Color(0xFFAB92F8),
                            unfocusedContainerColor = Color(0xFFAB92F8),
                            focusedIndicatorColor = Color(0xFFF4F2F8),
                            unfocusedIndicatorColor = Color(0xFFF4F2F8),
                        )
                    )
                    IconButton(onClick = {
                        var call = RetrofitFactory().getCepService().getEnderecosByUfCidade(
                            uf = ufState,
                            cidade = cidadeState,
                            rua = ruaState
                        )
                        call.enqueue(object : Callback<List<Endereco>> {
                            override fun onResponse(
                                call: Call<List<Endereco>>,
                                response: Response<List<Endereco>>
                            ) {
                                listaCepStates = response.body()!!
                            }

                            override fun onFailure(
                                call: Call<List<Endereco>?>,
                                t: Throwable
                            ) {
                                Log.i("FIAP", "onResponse: ${t.message}")
                            }
                        })
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Icone de Pesquisa",
                            tint = Color(0xFF4A148C)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(listaCepStates.size) {
                CardEndereco(endereco = listaCepStates[it])
            }
        }
    }
}

@Composable
fun CardEndereco(endereco: Endereco) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
            .clip(
                RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(Color(0xFF7D5BEA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "CEP: ${endereco.cep}", color = Color.White)
            Text(text = "Rua: ${endereco.rua}", color = Color.White)
            Text(text = "Cidade: ${endereco.cidade}", color = Color.White)
            Text(text = "Bairro: ${endereco.bairro}", color = Color.White)
            Text(text = "UF: ${endereco.uf}", color = Color.White)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CepScreenPreview() {
    ConsultaCEPTheme {
        CepScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardPreview() {
    ConsultaCEPTheme {
        CardEndereco(endereco = Endereco())
    }
}