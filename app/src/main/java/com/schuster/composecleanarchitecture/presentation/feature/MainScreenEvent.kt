package com.schuster.composecleanarchitecture.presentation.feature

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Cada classe dentro desta interface selada representa exatamente um tipo de intenção ou evento 
 * iniciado pelo usuário na tela. O único propósito deste arquivo é modelar essas intenções da UI.
 *
 * [SOLID: O - Open/Closed Principle (Princípio do Aberto/Fechado)]
 * Esta estrutura é altamente extensível. Se adicionarmos novas telas, botões ou ações na tela principal, 
 * podemos simplesmente declarar novos objetos ou classes de dados estendendo [MainScreenEvent] sem precisar
 * modificar os comportamentos das intenções existentes ou comprometer sua integridade.
 */
sealed interface MainScreenEvent {
    data class OnValueChange(val searchText: String) : MainScreenEvent
    data object OnSearch : MainScreenEvent
    data object OnClickSearch : MainScreenEvent
}