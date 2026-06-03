package com.schuster.composecleanarchitecture.data.api

import com.schuster.composecleanarchitecture.data.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A responsabilidade exclusiva desta interface é mapear os contratos de requisição HTTP da API externa
 * para que o Retrofit consiga gerar as chamadas de rede.
 *
 * [SOLID: I - Interface Segregation Principle (Princípio da Segregação de Interfaces)]
 * Esta interface contém apenas as assinaturas de endpoints referentes aos posts.
 * Outras operações (como autenticação ou gerenciamento de perfil) devem ser distribuídas em outras
 * interfaces de API dedicadas para evitar uma única interface gigantesca.
 */
interface PostApiService {

    @GET("/comments/{id}")
    suspend fun getPost(@Path("id") id: Int): PostResponse
}