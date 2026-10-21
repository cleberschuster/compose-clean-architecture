package com.schuster.composecleanarchitecture.domain.repository

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.Post

interface PostRepository {
    suspend fun getPost(id: Int): DomainResult<Post>
}
