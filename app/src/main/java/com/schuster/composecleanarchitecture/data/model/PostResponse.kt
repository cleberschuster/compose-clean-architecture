package com.schuster.composecleanarchitecture.data.model

import com.google.gson.annotations.SerializedName
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain

/**
 * [Clean Architecture: Data Model]
 * Aqui a nulidade é permitida e esperada, pois os dados vêm de uma fonte externa (API)
 * que pode falhar ou omitir campos.
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
 * O Mapper na camada de Data é responsável por "sanitizar" os dados.
 * Ele converte os nulos da API em valores seguros para o Domínio.
 */
fun PostResponse.toDomain() = ObjectDomain(
    postId = postId ?: 0,
    id = id ?: 0,
    email = email ?: "E-mail não informado",
    name = name ?: "Anônimo",
    comment = comment ?: ""
)
