package com.schuster.composecleanarchitecture.data.api

import com.schuster.composecleanarchitecture.data.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiService {

    @GET("/comments/{id}")
    suspend fun getPost(@Path("id") id: Int): PostResponse
}
