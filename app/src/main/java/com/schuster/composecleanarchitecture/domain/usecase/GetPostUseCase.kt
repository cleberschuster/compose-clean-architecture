package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import kotlinx.coroutines.flow.Flow

/**
 * [SOLID: I - Interface Segregation Principle (Princípio da Segregação de Interfaces)]
 * Clientes não devem ser forçados a depender de métodos que não usam.
 * Esta interface expõe uma única ação bem definida (o operador invoke). Qualquer classe
 * que precise buscar um post dependerá apenas desta assinatura mínima, em vez de depender
 * de um serviço complexo ou de uma classe com múltiplos métodos desnecessários.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * O ViewModel dependerá da interface/abstração [GetPostUseCase] e não da classe concreta 
 * [GetPostUseCaseImpl]. Isso facilita testes de unidade através de mocks e evita acoplamento forte.
 */
interface GetPostUseCase {
    suspend operator fun invoke(id: Int): Flow<ObjectDomain>
}