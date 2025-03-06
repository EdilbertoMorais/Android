package br.com.fiap.consultacep.service

import br.com.fiap.consultacep.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    //https://viacep.com.br/ws/ -> Base URL
    //https://viacep.com.br/ws/01001000/json/

    @GET("{cep}/json/")
    fun getEnderecoByCep(@Path("cep") cep: String): Call<Endereco>

    @GET("{uf}/{cidade}/{rua}/json/")
    fun getEnderecosByUfCidade(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("rua") rua: String
    ): Call<List<Endereco>>
}
