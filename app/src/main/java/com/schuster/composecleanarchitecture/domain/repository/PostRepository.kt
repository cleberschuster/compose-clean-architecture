package com.schuster.composecleanarchitecture.domain.repository

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain

/**
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * Esta interface estabelece um contrato que a camada de dados (infraestrutura) deve implementar.
 * Em vez de a camada de domínio depender da camada de dados para buscar informações da API, 
 * a camada de dados é quem depende deste contrato de domínio para se plugar ao sistema.
 *
 * [SOLID: I - Interface Segregation Principle (Princípio da Segregação de Interfaces)]
 * Esta interface define exclusivamente operações relacionadas a Posts/Comments. Caso o aplicativo
 * cresça e precise de novos serviços (ex. Autenticação, Usuários), criamos novas interfaces 
 * separadas em vez de inflar uma única interface de repositório monolítica.
 */
interface PostRepository {
    suspend fun getPost(id: Int): DomainResult<ObjectDomain>
}
