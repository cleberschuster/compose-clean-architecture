package com.schuster.composecleanarchitecture.data.mapper

import com.schuster.composecleanarchitecture.data.model.PostResponse
import com.schuster.composecleanarchitecture.domain.model.Post

/**
 * [Clean Architecture: Data Mapper]
 * Converte a DTO da API (PostResponse) para a Entidade de Domínio (Post),
 * tratando campos nulos com valores neutros e seguros.
 */
fun PostResponse.toDomain() = Post(
    postId = postId ?: 0,
    id = id ?: 0,
    email = email.orEmpty(),
    name = name.orEmpty(),
    comment = comment.orEmpty()
)
