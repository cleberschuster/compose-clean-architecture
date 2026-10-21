package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.Post
import com.schuster.composecleanarchitecture.domain.repository.PostRepository

/**
 * [Clean Architecture: Use Case]
 * Mediador entre a interface do repositório e a camada de apresentação.
 */
class GetPostUseCaseImpl(
    private val repository: PostRepository
) : GetPostUseCase {

    override suspend operator fun invoke(id: Int): DomainResult<Post> {
        return repository.getPost(id)
    }
}
