package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.repository.PostRepository

/**
 * [Clean Architecture: Use Case]
 * O Use Case agora atua como um mediador direto entre a interface do repositório
 * e a camada de apresentação. Implementar regra de negocio quando necessário
 */
class GetPostUseCaseImpl(
    private val repository: PostRepository
) : GetPostUseCase {

    override suspend operator fun invoke(id: Int): DomainResult<ObjectDomain> {
        return repository.getPost(id)
    }
}
