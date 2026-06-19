package com.schuster.composecleanarchitecture.presentation.feature

sealed interface PostIntent {
    data class OnValueChange(val searchText: String) : PostIntent
    data object OnSearch : PostIntent
}
