package com.schuster.composecleanarchitecture.presentation.feature

import android.content.Context
import androidx.annotation.StringRes

sealed interface MainUiEffect {
    class ShowSnackbar(
        @param:StringRes val resId: Int,
        vararg val args: Any
    ) : MainUiEffect

    fun asString(context: Context?): String {
        return when (this) {
            is ShowSnackbar -> context?.getString(resId, args).orEmpty()
        }
    }
}