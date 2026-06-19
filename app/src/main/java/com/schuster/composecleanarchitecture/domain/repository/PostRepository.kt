package com.schuster.composecleanarchitecture.domain.repository

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
interface PostRepository {
    suspend fun getPost(id: Int): DomainResult<ObjectDomain>
}
