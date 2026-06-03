package com.schuster.composecleanarchitecture.data.repository

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.model.toDomain
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * A responsabilidade exclusiva desta classe é buscar posts a partir da API remota utilizando a
 * interface [PostApiService] e converter o resultado para o modelo aceito pelo domínio.
 * Ela não toma nenhuma decisão de negócio ou de lógica de apresentação.
 *
 * [SOLID: L - Liskov Substitution Principle (Princípio da Substituição de Liskov)]
 * Como [PostRepositoryImpl] implementa a interface [PostRepository] de forma fiel, qualquer cliente
 * que dependa da interface [PostRepository] pode utilizá-la sem precisar conhecer a implementação concreta,
 * sem quebras de contrato ou comportamentos inesperados.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * O repositório depende de abstrações injetadas no seu construtor (como o [CoroutineDispatcher] e o
 * [PostApiService]). Não há acoplamento rígido de instanciação manual de dependências aqui.
 */
class PostRepositoryImpl(
    private val api: PostApiService,
    private val dispatcherIO: CoroutineDispatcher
) : PostRepository {

    override suspend fun getPost(id: Int): Flow<ObjectDomain> = flow {
        val response = api.getPost(id)
        emit(response.toDomain())
    }.flowOn(dispatcherIO)
}



