package com.schuster.composecleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

/**
 * [Clean Architecture: Data Model]
 * Aqui a nulidade é permitida e esperada para mapear respostas de APIs.
 */
data class PostResponse(
    @SerializedName("postId")
    val postId: Int? = null,
    
    @SerializedName("id")
    val id: Int? = null,
    
    @SerializedName("email")
    val email: String? = null,
    
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("body")
    val comment: String? = null
)
