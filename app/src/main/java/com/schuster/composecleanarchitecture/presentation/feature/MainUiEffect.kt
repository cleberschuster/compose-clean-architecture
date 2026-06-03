package com.schuster.composecleanarchitecture.presentation.feature

import android.content.Context
import androidx.annotation.StringRes

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A responsabilidade exclusiva desta interface selada é definir eventos de disparo único (side-effects)
 * na interface do usuário (ex: exibir Snackbars, Toasts, ou realizar navegações). Ela isola essas ações
 * transientes do estado persistente [UiState].
 *
 * [SOLID: O - Open/Closed Principle (Princípio do Aberto/Fechado)]
 * Esta estrutura permite fácil expansão. Podemos adicionar novos efeitos colaterais (como navegação ou
 * exibição de diálogos) declarando novas implementações de [MainUiEffect], sem a necessidade de reescrever
 * a lógica existente de Snackbars.
 */
sealed interface MainUiEffect {
    class ShowSnackbar(
        @param:StringRes val resId: Int,
        vararg val args: Any
    ) : MainUiEffect

    fun asString(context: Context?): String {
        return when (this) {
            is ShowSnackbar -> context?.getString(resId, *args).orEmpty()
        }
    }
}