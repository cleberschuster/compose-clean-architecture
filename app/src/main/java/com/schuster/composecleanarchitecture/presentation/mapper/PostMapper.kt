package com.schuster.composecleanarchitecture.presentation.mapper

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

/**
 * [Clean Architecture: Presentation Mapper]
 * Converte o modelo de Domínio para o formato ideal de exibição na UI.
 * Aqui podemos formatar strings, converter números para texto, etc.
 */
fun ObjectDomain.toPresentation() = ObjectPresentation(
    postId = postId.toString(),
    id = id.toString(),
    name = name,
    email = email,
    comment = comment
)
