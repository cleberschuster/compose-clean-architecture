package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A responsabilidade exclusiva desta classe é servir de modelo de dados imutável para representar
 * o estado completo da interface do usuário em um determinado instante de tempo.
 * Isolar o estado em uma única classe imutável evita inconsistências e simplifica a renderização declarativa
 * no Jetpack Compose (Single Source of Truth).
 */
data class UiState(
    val status: Status = Status.IDLE,
    val data: ObjectPresentation? = null,
    val errorMessage: String? = null,
    val textSearch: String = ""
)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    INPUT_TEXT_ERROR,
    IDLE
}


