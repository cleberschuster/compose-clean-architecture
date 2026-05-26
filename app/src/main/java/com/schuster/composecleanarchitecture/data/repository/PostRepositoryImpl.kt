package com.schuster.composecleanarchitecture.data.repository

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.mapper.ObjectToDomainMapper
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

    private val mapper = ObjectToDomainMapper()

    override suspend fun getPost(id: Int): Flow<ObjectDomain> = flow {
        val response = api.getPost(id)
        emit(mapper.map(response))
    }.flowOn(dispatcherIO)
}


