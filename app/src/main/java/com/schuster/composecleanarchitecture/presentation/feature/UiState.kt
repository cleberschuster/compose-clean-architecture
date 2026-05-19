package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

data class UiState(
    val status: Status = Status.IDLE,
    val data: ObjectPresentation? = null,
    val errorMessage: String ? = null,
)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    INPUT_TEXT_ERROR,
    IDLE
}

