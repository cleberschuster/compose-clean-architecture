package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

/**
 * [SOLID: S - Single Responsibility Principle]
 * Representa o estado completo da tela. O uso de uma sealed interface para o [Status]
 * garante que os dados (como o post ou o erro) existam apenas quando o estado permitir.
 */
data class UiState(
    val textSearch: String = "",
    val status: Status = Status.Idle
)

sealed interface Status {
    object Idle : Status
    object Loading : Status
    object InputTextError : Status
    data class Success(val data: ObjectPresentation) : Status
    data class Error(val message: String) : Status
}
