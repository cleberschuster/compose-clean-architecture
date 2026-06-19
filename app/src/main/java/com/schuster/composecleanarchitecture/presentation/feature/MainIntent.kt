package com.schuster.composecleanarchitecture.presentation.feature

sealed interface MainIntent {
    data class OnValueChange(val searchText: String) : MainIntent
    data object OnSearch : MainIntent
}
