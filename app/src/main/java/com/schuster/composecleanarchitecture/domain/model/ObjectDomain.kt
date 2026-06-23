package com.schuster.composecleanarchitecture.domain.model

/**
 * [Clean Architecture: Domain Model]
 * No domínio, definimos o que é essencial para o negócio.
 * Seguindo as melhores práticas do Kotlin, removemos a nulidade de campos obrigatórios
 * para garantir que as camadas internas (UseCase, Presentation) trabalhem com dados seguros.
 */
data class ObjectDomain(
    val postId: Int,
    val id: Int,
    val email: String,
    val name: String,
    val comment: String
)
