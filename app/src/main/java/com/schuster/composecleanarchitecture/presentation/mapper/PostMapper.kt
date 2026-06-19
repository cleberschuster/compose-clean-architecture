package com.schuster.composecleanarchitecture.presentation.mapper

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

fun ObjectDomain.toPresentation() = ObjectPresentation(
    postId = postId,
    id = id,
    name = name,
    email = email,
    comment = comment
)
