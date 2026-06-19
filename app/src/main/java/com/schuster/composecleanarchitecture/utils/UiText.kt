package com.schuster.composecleanarchitecture.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * [Clean Architecture / Presentation Layer]
 * Wrapper para representar textos na camada de apresentação de forma agnóstica de contexto.
 * Permite encapsular tanto strings vindas da internet (DynamicString) quanto strings localizadas
 * de recursos do Android (StringResource), facilitando testes e mantendo o ViewModel livre
 * de referências ao Context do Android.
 */
sealed interface UiText {
    
    data class DynamicString(val value: String) : UiText

    class StringResource(
        @param:StringRes val resId: Int,
        vararg val args: Any
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}
