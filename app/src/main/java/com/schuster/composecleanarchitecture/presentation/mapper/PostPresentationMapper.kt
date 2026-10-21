package com.schuster.composecleanarchitecture.presentation.mapper

import com.schuster.composecleanarchitecture.domain.model.Post
import com.schuster.composecleanarchitecture.presentation.model.PostUiModel

/**
 * [Clean Architecture: Presentation Mapper]
 * Converte o modelo de Domínio (Post) para o formato ideal de exibição na UI (PostUiModel),
 * aplicando formatações específicas para tela.
 */
fun Post.toPresentation() = PostUiModel(
    postId = postId.toString(),
    id = id.toString(),
    name = name.ifBlank { "Nome não informado" },
    email = email.lowercase(),
    comment = comment.ifBlank { "Sem comentário" }
)
