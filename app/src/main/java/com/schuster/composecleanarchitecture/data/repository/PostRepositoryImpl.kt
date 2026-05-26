package com.schuster.composecleanarchitecture.data.repository

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.model.toDomain
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepositoryImpl(
    private val api: PostApiService,
    private val dispatcherIO: CoroutineDispatcher
) : PostRepository {

    override suspend fun getPost(id: Int): Flow<ObjectDomain> = flow {
        val response = api.getPost(id)
        emit(response.toDomain())
    }.flowOn(dispatcherIO)
}


