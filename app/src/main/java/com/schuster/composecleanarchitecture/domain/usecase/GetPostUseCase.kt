package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.Post

interface GetPostUseCase {
    suspend operator fun invoke(id: Int): DomainResult<Post>
}
