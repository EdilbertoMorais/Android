package br.com.fiap.textcomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.textcomponent.ui.theme.TextComponentTheme
import br.com.fiap.textcomponent.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextComponentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

    var numeroTelefone by remember {
        mutableStateOf("")
    }
    var nomeUsuario by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var kotlin by remember {
        mutableStateOf(false)
    }
    var java by remember {
        mutableStateOf(false)
    }
    var verdeAtypical by remember {
        mutableStateOf(Color(0xFFB3FF04))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
            .background(Color.Black),
    ) {
        Text(
            modifier = Modifier
                .background(Color(3, 3, 3, 255))
                .padding(6.dp)
                .padding(horizontal = 25.dp)
                .align(Alignment.CenterHorizontally),
            text = "Estudando Programação Android",
            lineHeight = 50.sp,
            fontSize = 32.sp,
            color = verdeAtypical,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            fontFamily = anton
        )

        OutlinedTextField(
            value = nomeUsuario,
            onValueChange = { letra ->
                nomeUsuario = letra
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 16.dp),
            label = {
                Text(
                    text = "Digite seu Nome",
                    color = verdeAtypical,
                )
            },
            placeholder = {
                Text(
                    "Nome e Sobrenome",
                    color = verdeAtypical,
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.person_24),
                    contentDescription = "Ícone de um usuário",
                    tint = verdeAtypical
                )
            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            textStyle = TextStyle(
                color = verdeAtypical,
                fontSize = 20.sp
            ),
            shape = RoundedCornerShape(32.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = verdeAtypical,
                unfocusedBorderColor = verdeAtypical,
                cursorColor = verdeAtypical,
                focusedContainerColor = Color(0xFF211F1F)
            )
        )

        OutlinedTextField(
            value = numeroTelefone,
            onValueChange = { numero ->
                numeroTelefone = numero
            }, singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 16.dp),
            label = {
                Text(
                    text = "Digite seu Telefone",
                    color = verdeAtypical
                )
            },
            placeholder = {
                Text(
                    "Exemplo: (99) 99999-9999",
                    color = verdeAtypical
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "Ícone de um telefone",
                    tint = verdeAtypical
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                color = verdeAtypical,
                fontSize = 20.sp
            ),
            shape = RoundedCornerShape(32.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = verdeAtypical,
                unfocusedBorderColor = verdeAtypical,
                cursorColor = verdeAtypical,
                focusedContainerColor = Color(0xFF211F1F)
            )
        )

        OutlinedTextField(
            value = email,
            onValueChange = { caractere -> email = caractere },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 16.dp),
            singleLine = true,
            label = {
                Text(
                    text = "Digite seu E-mail",
                    color = verdeAtypical
                )
            },
            placeholder = {
                Text(
                    "exemplo@dominio.com",
                    color = verdeAtypical
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.email_24),
                    contentDescription = "Ícone de um e-mail",
                    tint = verdeAtypical
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                color = verdeAtypical,
                fontSize = 20.sp
            ),
            shape = RoundedCornerShape(32.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = verdeAtypical,
                unfocusedBorderColor = verdeAtypical,
                cursorColor = verdeAtypical,
                focusedContainerColor = Color(0xFF211F1F)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Selecione a Linguagem?",
            color = verdeAtypical,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = kotlin,
                    onCheckedChange = { isSelecao ->
                        kotlin = isSelecao
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        checkmarkColor = verdeAtypical,
                        uncheckedColor = verdeAtypical
                    )
                )
                Text(
                    text = "Kotlin",
                    color = verdeAtypical
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = java,
                    onCheckedChange = { isSelecao ->
                        java = isSelecao
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        checkmarkColor = verdeAtypical,
                        uncheckedColor = verdeAtypical
                    )
                )
                Text(
                    text = "Java",
                    color = verdeAtypical
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Você está matriculado no curso?",
            color = verdeAtypical,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        var linguagemSelecionada by remember {
            mutableIntStateOf(0)
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = linguagemSelecionada == 1,
                    onClick = {
                        linguagemSelecionada = 1
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = verdeAtypical,
                        unselectedColor = verdeAtypical
                    )
                )
                Text(
                    text = "Sim",
                    color = verdeAtypical
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = linguagemSelecionada == 2,
                    onClick = {
                        linguagemSelecionada = 2
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = verdeAtypical,
                        unselectedColor = verdeAtypical
                    )
                )
                Text(
                    text = "Não",
                    color = verdeAtypical
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Mudar cor do texto:",
            color = verdeAtypical,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 5.dp)
        ) {
            Button(
                onClick = {
                    verdeAtypical = Color(0xFFFF5D18)
                },
                modifier = Modifier.size(width = 150.dp, height = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = verdeAtypical,
                    contentColor = Color.Black
                )
            ) {
                Row(horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                    ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Botão de edição")
                    Text(text = "Cor Laranja")
                }
            }

            Button(
                onClick = {
                    verdeAtypical = Color(0xFFB3FF04)
                },
                modifier = Modifier.size(width = 150.dp, height = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = verdeAtypical,
                    contentColor = Color.Black
                )
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Botão de edição")
                    Text(text = "Cor Verde")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TextComponentTheme {
        Greeting()
    }
}