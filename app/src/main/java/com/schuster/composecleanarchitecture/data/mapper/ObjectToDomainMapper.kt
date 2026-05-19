package com.schuster.composecleanarchitecture.data.mapper

import com.schuster.composecleanarchitecture.data.model.PostResponse
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.utils.Mapper

/*
* Esta classe transforma um objeto da camada de DATA para um objeto da camada de DOMAIN.
* Lembre-se: Quanto mais isoladas suas camadas forem, maior sua
* flexibilidade para realizar mudanças sem gerar grandes impactos.
*/

class ObjectToDomainMapper: Mapper<PostResponse, ObjectDomain> {

    override fun map(source: PostResponse): ObjectDomain {
        return ObjectDomain(
            postId = source.postId,
            id = source.id,
            email = source.email,
            name = source.name,
            comment = source.comment
        )
    }
}