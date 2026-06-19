package com.schuster.composecleanarchitecture.data.repository

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.model.toDomain
import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import com.schuster.composecleanarchitecture.utils.handleApiError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PostRepositoryImpl(
    private val api: PostApiService,
    private val dispatcherIO: CoroutineDispatcher
) : PostRepository {

    override suspend fun getPost(id: Int): DomainResult<ObjectDomain> = withContext(dispatcherIO) {
        try {
            val response = api.getPost(id)
            DomainResult.Success(response.toDomain())
        } catch (e: Exception) {
            DomainResult.Error(handleApiError(e).toString())
        }
    }
}



