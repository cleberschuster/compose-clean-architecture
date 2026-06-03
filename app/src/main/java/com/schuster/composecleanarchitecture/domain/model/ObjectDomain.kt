package com.schuster.composecleanarchitecture.domain.model

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Esta classe tem uma única razão para mudar: a definição de negócio do objeto de domínio.
 * Ela não deve se preocupar com serialização de rede (como Gson), persistência em banco de dados
 * ou renderização de tela. Sua única responsabilidade é representar a entidade do domínio.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * A camada de Domínio está no centro da Clean Architecture e não deve depender de nenhuma outra
 * camada ou framework externo (como com.google.gson.annotations.SerializedName). 
 * Ao removermos as anotações do Gson deste arquivo, garantimos que o Domínio permaneça puro
 * e independente de detalhes de infraestrutura (frameworks de rede/banco).
 */
data class ObjectDomain(
    val postId: Int? = null,
    val id: Int? = null,
    val email: String? = null,
    val name: String? = null,
    val comment: String? = null
)