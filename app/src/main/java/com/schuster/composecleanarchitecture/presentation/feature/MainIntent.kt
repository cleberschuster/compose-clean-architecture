package com.schuster.composecleanarchitecture.presentation.feature

/**
 * [MVI: Intent]
 * Representa as intenções do usuário ou eventos que disparam uma mudança de estado ou efeito.
 *
 * [SOLID: S - Single Responsibility Principle]
 * Cada classe dentro desta interface selada representa exatamente um tipo de intenção 
 * iniciado pelo usuário na tela. O único propósito deste arquivo é modelar essas intenções.
 *
 * [SOLID: O - Open/Closed Principle]
 * Facilmente extensível para novas ações do usuário sem modificar a lógica existente.
 */
sealed interface MainIntent {
    data class OnValueChange(val searchText: String) : MainIntent
    data object OnSearch : MainIntent
    data object OnClickSearch : MainIntent
}
