package com.schuster.composecleanarchitecture.data.model

import com.google.gson.annotations.SerializedName
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain

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

fun PostResponse.toDomain() = ObjectDomain(
    postId = postId,
    id = id,
    email = email,
    name = name,
    comment = comment
)
