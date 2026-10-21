package com.schuster.composecleanarchitecture.data.repository

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.mapper.toDomain
import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.Post
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import com.schuster.composecleanarchitecture.utils.handleApiError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * [SOLID: S - Single Responsibility Principle]
 * Busca posts a partir da API remota utilizando [PostApiService] e converte
 * o resultado para o modelo de domínio [Post].
 */
class PostRepositoryImpl(
    private val api: PostApiService,
    private val dispatcherIO: CoroutineDispatcher
) : PostRepository {

    override suspend fun getPost(id: Int): DomainResult<Post> = withContext(dispatcherIO) {
        try {
            val response = api.getPost(id)
            DomainResult.Success(response.toDomain())
        } catch (e: Exception) {
            DomainResult.Error(handleApiError(e))
        }
    }
}
