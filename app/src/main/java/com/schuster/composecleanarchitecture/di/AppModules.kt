package com.schuster.composecleanarchitecture.di

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.datasource.RemotePostDataSource
import com.schuster.composecleanarchitecture.data.datasource.RemotePostDataSourceImpl
import com.schuster.composecleanarchitecture.data.repository.PostRepositoryImpl
import com.schuster.composecleanarchitecture.data.retrofit.RetrofitService
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import com.schuster.composecleanarchitecture.domain.usecase.GetPostUseCase
import com.schuster.composecleanarchitecture.domain.usecase.GetPostUseCaseImpl
import com.schuster.composecleanarchitecture.presentation.feature.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
* Neste arquivo você deve declarar todas as suas dependências injetadas.
* Lembre-se de manter cada classe em sua camada, como feito abaixo.
* Obs.: Se o arquivo ficar muito grande, é melhor criar um arquivo para cada camada.
*/

val domainModules = module {
    factory<GetPostUseCase> { GetPostUseCaseImpl(repository = get()) }
}

val presentationModules = module {
    viewModel { MainViewModel(useCase = get()) }
}

val dataModules = module {
    factory<RemotePostDataSource> { RemotePostDataSourceImpl(api = get()) }
    factory<PostRepository> { PostRepositoryImpl(remoteDataSource = get(), dispatcherIO = Dispatchers.IO) }
}

val networkModules = module {
    single { RetrofitService.create<PostApiService>() }
}

val anotherModules = module {}
