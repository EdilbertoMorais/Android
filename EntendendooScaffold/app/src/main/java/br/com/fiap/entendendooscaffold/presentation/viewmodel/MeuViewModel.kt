package br.com.fiap.entendendooscaffold.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.entendendooscaffold.domain.model.MeuDado
import br.com.fiap.entendendooscaffold.domain.usecase.ObterMeuDadoUseCase

class MeuViewModel : ViewModel() {
    private val _meuDado = MutableLiveData<MeuDado>()
    val meuDado: LiveData<MeuDado> = _meuDado

    private val obterMeuDadoUseCase = ObterMeuDadoUseCase()

    fun obterDado(){
        _meuDado.value = obterMeuDadoUseCase()

    }
}