package com.schuster.composecleanarchitecture.data.datasource

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain

interface RemotePostDataSource {

    suspend fun getPost(id: Int): ObjectDomain
}