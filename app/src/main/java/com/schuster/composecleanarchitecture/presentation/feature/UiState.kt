package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

data class UiState(
    val textSearch: String = "",
    val status: MainStatus = MainStatus.Idle
)

sealed interface MainStatus {
    data object Idle : MainStatus
    data object Loading : MainStatus
    data object InputTextError : MainStatus
    data class Success(val data: ObjectPresentation) : MainStatus
    data class Error(val message: String) : MainStatus
}
