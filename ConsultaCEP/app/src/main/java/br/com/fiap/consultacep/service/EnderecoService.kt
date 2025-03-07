package br.com.fiap.consultacep.service

import br.com.fiap.consultacep.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {
    //https://viacep.com.br/ws/ -> Base URL


    //https://viacep.com.br/ws/01001000/json/ -> Exemplo de chamada
    @GET("{cep}/json/")
    fun getEnderecoByCep(@Path("cep") cep: String): Call<Endereco>

    //https://viacep.com.br/ws/RS/Porto%20Alegre/Domingos/json/ -> Exemplo de chamada
    @GET("{uf}/{cidade}/{rua}/json/")
    fun getEnderecosByUfCidade(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("rua") rua: String
    ): Call<List<Endereco>>
}
