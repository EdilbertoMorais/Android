package br.com.fiap.calculadoradejurossimples.componentes

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaDeEntrada(
    label: String,
    labelColor: Color,
    placeHolder: String,
    placeHolderColor: Color,
    value: String,
    keyboardType: KeyboardType,
    modifier: Modifier,
    atualizarValor: (String) -> Unit,
    singleLine: Boolean,
    focusedBorderColor: Color = Color.Gray,
    unfocusedBorderColor: Color = Color.LightGray,
    textStyleColor: Color = Color.White
) {
    OutlinedTextField(
        value = value,
        onValueChange = atualizarValor,
        label = { Text(text = label, style = TextStyle(color = labelColor)) },
        placeholder = { Text(text = placeHolder, style = TextStyle(color = placeHolderColor)) },
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unfocusedBorderColor,
            cursorColor = labelColor,
            focusedLabelColor = labelColor,
            unfocusedLabelColor = placeHolderColor
        ),
        textStyle = TextStyle(color = textStyleColor),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine
    )
}

@Preview
@Composable
fun CaixaDeEntradaPreview(showSystemUi: Boolean = true) {
    CaixaDeEntrada(
        "Valor Investimento",
        Color.White,
        "Qual valor para investir",
        Color.Yellow,
        "teste",
        KeyboardType.Text,
        Modifier,
        {},
        true,
        focusedBorderColor = Color.Yellow,
        unfocusedBorderColor = Color.Magenta,
        textStyleColor = Color.LightGray,
    )
}