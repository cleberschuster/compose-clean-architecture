package com.schuster.composecleanarchitecture.utils

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A responsabilidade exclusiva deste objeto utilitário é manter centralizadas as chaves/tags de teste
 * do Jetpack Compose para uso nos testes automatizados, evitando o uso de strings soltas espalhadas pelo código.
 */
object TestTags {
    const val COMMENT_TEXT = "COMMENT_TEXT"
    const val LOADING = "LOADING"
    const val CLICK_SEARCH = "CLICK_SEARCH"
}