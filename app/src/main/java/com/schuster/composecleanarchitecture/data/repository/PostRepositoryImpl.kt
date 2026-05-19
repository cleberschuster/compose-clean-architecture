package com.schuster.composecleanarchitecture.data.repository

import com.schuster.composecleanarchitecture.data.datasource.RemotePostDataSource
import com.schuster.composecleanarchitecture.domain.mapper.ObjectToPresentationMapper
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/*
* Esta camada é responsável por chavear entre as fontes de dados.
* Por exemplo, se você tivesse armazenamento local (usando ROOM, por exemplo),
* você teria um localDataSource, e este repository seria responsável pela
* lógica de saber se pega os dados localmente ou da API.
*
* Após a chamada, é realizado a transformação do objeto da camada de DOMAIN
* para um objeto da camada de PRESENTATION por meio do mapper.
*
*/

class PostRepositoryImpl(
    private val remoteDataSource: RemotePostDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : PostRepository {

    private val mapper: ObjectToPresentationMapper = ObjectToPresentationMapper()

    override suspend fun getPost(id: Int): Flow<ObjectPresentation> = flow {

        val response = mapper.map(remoteDataSource.getPost(id))
        emit(response)

    }.flowOn(dispatcherIO)
}


