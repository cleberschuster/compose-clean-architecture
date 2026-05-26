package com.schuster.composecleanarchitecture.presentation.feature

import androidx.annotation.StringRes

sealed interface MainUiEffect {
    data class ShowSnackbar(
        @param:StringRes val resId: Int,
        val args: Array<out Any> = emptyArray()
    ) : MainUiEffect {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as ShowSnackbar
            if (resId != other.resId) return false
            if (!args.contentEquals(other.args)) return false
            return true
        }

        override fun hashCode(): Int {
            var result = resId
            result = 31 * result + args.contentHashCode()
            return result
        }
    }
}