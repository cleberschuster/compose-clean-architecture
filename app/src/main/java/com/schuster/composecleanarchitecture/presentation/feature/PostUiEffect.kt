package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.utils.UiText

/**
 * [MVI: Side-Effect]
 * Representa eventos de disparo único que não alteram o estado persistente da UI,
 * como exibição de Snackbars, Toasts ou Navegação.
 *
 * [SOLID: S - Single Responsibility Principle]
 * Isola ações transientes do estado [PostUiState].
 * Este modelo é um dado puro: não depende de Context nem de nenhum framework Android.
 * A conversão para String (que requer Context) é responsabilidade exclusiva da View (PostScreen).
 */
sealed interface PostUiEffect {
    data class ShowSnackbar(val message: UiText) : PostUiEffect
}
