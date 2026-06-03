package com.schuster.composecleanarchitecture.data.model

import com.google.gson.annotations.SerializedName
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Este objeto (DTO - Data Transfer Object) tem a única responsabilidade de representar a estrutura
 * de dados bruta retornada pela API externa. Mesmo que os nomes dos campos correspondam aos do
 * domínio, mantemos esse objeto isolado para que qualquer alteração no contrato da API (ex. renomear campos)
 * afete apenas esta classe, impedindo que o impacto se propague para o domínio e as telas do aplicativo.
 */
data class PostResponse(
    @SerializedName("postId")
    val postId: Int? = null,
    
    @SerializedName("id")
    val id: Int? = null,
    
    @SerializedName("email")
    val email: String? = null,
    
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("body")
    val comment: String? = null
)

/**
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * Função de extensão que converte o modelo de infraestrutura de dados (PostResponse) para
 * o modelo conceitual do domínio (ObjectDomain).
 * Isso garante a direção de dependência correta na Clean Architecture: a camada de dados
 * conhece a de domínio e se adapta a ela, e a camada de domínio permanece 100% livre
 * de acoplamentos com detalhes de rede ou anotações externas (como @SerializedName).
 */
fun PostResponse.toDomain() = ObjectDomain(
    postId = postId,
    id = id,
    email = email,
    name = name,
    comment = comment
)
