package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.repository.PostRepository

class GetPostUseCaseImpl(
    private val repository: PostRepository
) : GetPostUseCase {

    override suspend operator fun invoke(id: Int): DomainResult<ObjectDomain> {
        return when (val result = repository.getPost(id)) {
            is DomainResult.Success -> {
                val post = result.data
                if (post.id != null && post.id < 1000) {
                    DomainResult.Success(post)
                } else {
                    DomainResult.Error("Post não encontrado ou ID inválido (Regra de Negócio)")
                }
            }
            is DomainResult.Error -> result
        }
    }
}
