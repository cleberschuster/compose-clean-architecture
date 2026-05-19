package com.schuster.composecleanarchitecture.domain.repository

import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPost(id: Int): Flow<ObjectPresentation>
}