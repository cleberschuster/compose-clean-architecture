package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class GetPostUseCaseImpl(
    private val repository: PostRepository
) : GetPostUseCase {

    override suspend operator fun invoke(id: Int): Flow<ObjectPresentation> =

        repository.getPost(id).filter {
            it.id!! < 1000
        }
}