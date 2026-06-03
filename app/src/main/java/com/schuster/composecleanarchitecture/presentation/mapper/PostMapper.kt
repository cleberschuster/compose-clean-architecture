package com.schuster.composecleanarchitecture.presentation.mapper

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A responsabilidade exclusiva deste arquivo/função de extensão é realizar o mapeamento entre 
 * o modelo de domínio [ObjectDomain] e o modelo específico de exibição [ObjectPresentation].
 * Extrair este mapeamento do ViewModel evita o acúmulo desnecessário de responsabilidade
 * na lógica de gerenciamento de estado e fluxo.
 */
fun ObjectDomain.toPresentation() = ObjectPresentation(
    postId = postId,
    id = id,
    name = name,
    email = email,
    comment = comment
)
