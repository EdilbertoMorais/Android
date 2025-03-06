package br.com.fiap.entendendooscaffold.domain.usecase

import br.com.fiap.entendendooscaffold.domain.model.MeuDado

class ObterMeuDadoUseCase {
    operator fun invoke(): MeuDado {
        //logica para obter o dado
        return MeuDado("Dado do caso de uso")
    }
}