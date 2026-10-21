package com.schuster.composecleanarchitecture.domain.model

/**
 * [Clean Architecture: Domain Model]
 * Representa a Entidade pura do domínio para um Post.
 * Sem dependências de frameworks externos ou de UI.
 */
data class Post(
    val postId: Int,
    val id: Int,
    val email: String,
    val name: String,
    val comment: String
)
