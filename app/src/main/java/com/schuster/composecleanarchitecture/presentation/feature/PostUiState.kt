package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

data class PostUiState(
    val textSearch: String = "",
    val status: PostStatus = PostStatus.Idle
)

sealed interface PostStatus {
    data object Idle : PostStatus
    data object Loading : PostStatus
    data object InputTextError : PostStatus
    data class Success(val data: ObjectPresentation) : PostStatus
    data class Error(val message: String) : PostStatus
}
