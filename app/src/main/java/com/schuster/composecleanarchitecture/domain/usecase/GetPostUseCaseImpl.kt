package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A única responsabilidade desta classe concreta é orquestrar a lógica do caso de uso "buscar post".
 * 
 * [SOLID: O - Open/Closed Principle (Princípio do Aberto/Fechado)]
 * A lógica deste caso de uso está isolada. Se as regras de negócio de busca de post mudarem,
 * nós modificamos ou estendemos esta classe (ou criamos outra implementação de [GetPostUseCase])
 * sem tocar no código das telas (Presentation) que o utilizam.
 *
 * [SOLID: L - Liskov Substitution Principle (Princípio da Substituição de Liskov)]
 * Esta implementação estende [GetPostUseCase] de forma previsível e segura. Evitamos usar '!!'
 * (force unwrap) que poderia causar crashes de NullPointerException inesperados, respeitando
 * os contratos de tipos definidos e garantindo a estabilidade da substituição.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * O UseCase não depende diretamente da implementação concreta do repositório (PostRepositoryImpl)
 * que fica na camada de dados, mas sim da abstração/interface [PostRepository] definida na camada de domínio.
 */
class GetPostUseCaseImpl(
    private val repository: PostRepository
) : GetPostUseCase {

    override suspend operator fun invoke(id: Int): Flow<ObjectDomain> =
        repository.getPost(id).filter { post ->
            val postId = post.id
            postId != null && postId < 1000
        }
}