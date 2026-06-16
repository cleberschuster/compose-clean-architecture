package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.repository.PostRepository

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
 * Esta implementação estende [GetPostUseCase] de forma previsível e segura.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * O UseCase não depende diretamente da implementação concreta do repositório (PostRepositoryImpl)
 * que fica na camada de dados, mas sim da abstração/interface [PostRepository] definida na camada de domínio.
 */
class GetPostUseCaseImpl(
    private val repository: PostRepository
) : GetPostUseCase {

    override suspend operator fun invoke(id: Int): DomainResult<ObjectDomain> {
        return when (val result = repository.getPost(id)) {
            is DomainResult.Success -> {
                val post = result.data
                if (post.id != null && post.id < 1000) {
                    DomainResult.Success(post)
                } else {
                    DomainResult.Error("Post não encontrado ou ID inválido (Regra de Negócio)")
                }
            }
            is DomainResult.Error -> result
        }
    }
}
