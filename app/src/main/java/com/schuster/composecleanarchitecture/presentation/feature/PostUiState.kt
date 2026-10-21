package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.presentation.model.PostUiModel

data class PostUiState(
    val textSearch: String = "",
    val status: PostStatus = PostStatus.Idle
)

sealed interface PostStatus {
    data object Idle : PostStatus
    data object Loading : PostStatus
    data object InputTextError : PostStatus
    data class Success(val data: PostUiModel) : PostStatus
    data class Error(val message: String) : PostStatus
}
