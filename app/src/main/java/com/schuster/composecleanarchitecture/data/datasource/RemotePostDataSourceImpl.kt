package com.schuster.composecleanarchitecture.data.datasource

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.mapper.ObjectToDomainMapper

/*
* Esta classe é responsável por chamar a API.
* Como o resultado retornado pela API é um objeto da camada de DATA, já
* é realizado o map para retornar um objeto da camada de DOMAIN.
*
* Obs.: Muita gente prefere injetar o Mapper direto no construtor, para
* aproveitar o uso do Koin. Esse ponto é válido de discussão.
* Eu, particularmente, acho que é um uso desnecessário de recursos, preferindo não injetar.
*
*/

class RemotePostDataSourceImpl(
    private val api: PostApiService
) : RemotePostDataSource {

    private val mapper: ObjectToDomainMapper = ObjectToDomainMapper()

    override suspend fun getPost(id: Int) = mapper.map(api.getPost(id))

}
