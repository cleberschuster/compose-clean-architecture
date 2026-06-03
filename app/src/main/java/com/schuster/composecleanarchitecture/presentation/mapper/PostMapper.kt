package com.schuster.composecleanarchitecture.presentation.mapper

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

/**
 * [SOLID: S - Single Responsibility Principle]
 * Este arquivo tem a única responsabilidade de mapear objetos da camada de Domínio
 * para a camada de Apresentação.
 */

fun ObjectDomain.toPresentation() = ObjectPresentation(
    postId = postId,
    id = id,
    name = name,
    email = email,
    comment = comment
)
