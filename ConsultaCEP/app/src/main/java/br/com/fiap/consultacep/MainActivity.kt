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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
            .padding(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "CONSULTA CEP", fontSize = 24.sp)
                Text(
                    text = "Encontre o seu Endereço",
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = cepState,
                    onValueChange = {
                        cepState = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Qual o CEP procurado?")
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
                                contentDescription = "Icone de Pesquisa"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Não sabe o CEP?",
                    fontWeight = FontWeight.Bold
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
                            Text(text = "UF?")
                        },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters,
                            keyboardType = KeyboardType.Text,
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = cidadeState,
                        onValueChange = {
                            cidadeState = it
                        },
                        modifier = Modifier.weight(2f),
                        label = {
                            Text(text = "Qual a cidade?")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        ),
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = ruaState,
                        onValueChange = {
                            ruaState = it
                        },
                        modifier = Modifier.weight(2f),
                        label = {
                            Text(text = "Digite o nome da rua:")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words,
                        ),
                        singleLine = true
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
                            contentDescription = "Icone de Pesquisa"
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "CEP: ${endereco.cep}")
            Text(text = "Rua: ${endereco.rua}")
            Text(text = "Cidade: ${endereco.cidade}")
            Text(text = "Bairro: ${endereco.bairro}")
            Text(text = "UF: ${endereco.uf}")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    ConsultaCEPTheme {
        CepScreen()
    }
}