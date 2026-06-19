package com.schuster.composecleanarchitecture.presentation.feature

import androidx.annotation.StringRes

/**
 * [MVI: Side-Effect]
 * Representa eventos de disparo único que não alteram o estado persistente da UI,
 * como exibição de Snackbars, Toasts ou Navegação.
 *
 * [SOLID: S - Single Responsibility Principle]
 * Isola ações transientes do estado [UiState].
 * Este modelo é um dado puro: não depende de Context nem de nenhum framework Android.
 * A conversão para String (que requer Context) é responsabilidade exclusiva da View (MainScreen).
 */
sealed interface MainUiEffect {
    data class ShowSnackbar(
        @param:StringRes val resId: Int,
        val args: List<Any> = emptyList()
    ) : MainUiEffect
}
