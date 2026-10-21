package com.schuster.composecleanarchitecture.presentation.model

/**
 * [Clean Architecture: Presentation Model / UI Model]
 * Modelo otimizado para exibição direta na interface do usuário (Jetpack Compose).
 */
data class PostUiModel(
    val postId: String,
    val id: String,
    val email: String,
    val name: String,
    val comment: String
)
