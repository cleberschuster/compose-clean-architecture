package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class GetPostUseCaseImpl(
    private val repository: PostRepository
) : GetPostUseCase {

    override suspend operator fun invoke(id: Int): Flow<ObjectDomain> =

        repository.getPost(id).filter {
            it.id!! < 1000
        }
}