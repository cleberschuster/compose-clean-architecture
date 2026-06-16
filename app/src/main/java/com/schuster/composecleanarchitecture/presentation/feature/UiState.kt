package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

/**
 * [MVI: State]
 * Representa o estado completo e imutável da tela em um determinado momento.
 *
 * [SOLID: S - Single Responsibility Principle]
 * O único propósito desta classe é manter os dados necessários para a renderização da UI.
 */
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
