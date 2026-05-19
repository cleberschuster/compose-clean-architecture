package br.com.schuster.androidcleanarchitecture.domain.usecase

import br.com.schuster.androidcleanarchitecture.domain.repository.PostRepository
import br.com.schuster.androidcleanarchitecture.presentation.model.ObjectPresentation
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