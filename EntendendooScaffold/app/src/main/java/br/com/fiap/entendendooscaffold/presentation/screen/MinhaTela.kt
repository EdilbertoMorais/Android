package br.com.fiap.entendendooscaffold.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import br.com.fiap.entendendooscaffold.presentation.viewmodel.MeuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinhaTela(){
    val viewModel = MeuViewModel()
    val dado = viewModel.meuDado.observeAsState().value
    viewModel.obterDado()

    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Meu App Clean") })
        }
    ) {innerPadding ->
        Text(
            text = dado?.texto ?: "Carregando...",
            modifier = Modifier.padding(innerPadding)
        )
    }

}