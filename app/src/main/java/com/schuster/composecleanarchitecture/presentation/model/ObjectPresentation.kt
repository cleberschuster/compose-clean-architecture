package com.schuster.composecleanarchitecture.presentation.model

/**
 * [Clean Architecture: Presentation Model]
 * Modelo otimizado para a UI. Ao remover nulidades aqui, evitamos verificações
 * constantes dentro dos componentes Compose, tornando o código da UI mais limpo.
 */
data class ObjectPresentation(
    val postId: String,
    val id: String,
    val email: String,
    val name: String,
    val comment: String
)
