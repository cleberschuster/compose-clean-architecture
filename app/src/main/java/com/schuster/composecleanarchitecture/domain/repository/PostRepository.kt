package com.schuster.composecleanarchitecture.domain.repository

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPost(id: Int): Flow<ObjectDomain>
}