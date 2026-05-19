package com.schuster.composecleanarchitecture.domain.mapper

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation
import com.schuster.composecleanarchitecture.utils.Mapper

class ObjectToPresentationMapper: Mapper<ObjectDomain, ObjectPresentation> {

    override fun map(source: ObjectDomain): ObjectPresentation {
        return ObjectPresentation(
            postId = source.postId,
            id = source.id,
            email = source.email,
            name = source.name,
            comment = source.comment
        )
    }
}