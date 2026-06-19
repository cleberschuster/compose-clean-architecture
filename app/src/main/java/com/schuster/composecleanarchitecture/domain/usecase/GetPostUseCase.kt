package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain

interface GetPostUseCase {
    suspend operator fun invoke(id: Int): DomainResult<ObjectDomain>
}
