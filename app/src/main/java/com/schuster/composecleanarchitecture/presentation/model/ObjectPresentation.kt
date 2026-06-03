package com.schuster.composecleanarchitecture.presentation.model

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Esta classe tem a responsabilidade exclusiva de representar o estado dos dados formatados 
 * para renderização direta na tela (camada de apresentação). Ela garante que a UI não precise 
 * se preocupar com os tipos ou formatos das camadas de dados ou domínio.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * Como modelo da camada de apresentação, este arquivo não deve ter dependências com bibliotecas
 * de parse de dados de rede (como Gson). A deserialização de rede é responsabilidade da camada
 * de dados e o mapeamento passa pelo domínio até chegar aqui de forma limpa.
 */
data class ObjectPresentation(
    val postId: Int? = null,
    val id: Int? = null,
    val email: String? = null,
    val name: String? = null,
    val comment: String? = null
)

