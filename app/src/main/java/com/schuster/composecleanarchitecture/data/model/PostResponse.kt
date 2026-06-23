package com.schuster.composecleanarchitecture.data.model

import com.google.gson.annotations.SerializedName
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain

/**
 * [Clean Architecture: Data Model]
 * Aqui a nulidade é permitida e esperada.
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

/**
 * O Mapper na camada de Data agora usa valores NEUTROS (vazio ou zero).
 * Ele não decide o texto que o usuário verá, apenas garante que o dado não seja nulo.
 */
fun PostResponse.toDomain() = ObjectDomain(
    postId = postId ?: 0,
    id = id ?: 0,
    email = email ?: "",
    name = name ?: "",
    comment = comment ?: ""
)
