package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import kotlinx.coroutines.flow.Flow

/*
* Esta classe é responsável por realizar os casos de uso, chamando seu repository.
* Deve-se ter um UseCase para cada ação do seu aplicativo.
* Exemplo: Se seu aplicativo pode salvar dados, excluir dados e pegar dados,
* então, deverá ter 3 classes UseCase distintos.
*
*/

interface GetPostUseCase {
    suspend operator fun invoke(id: Int): Flow<ObjectDomain>
}