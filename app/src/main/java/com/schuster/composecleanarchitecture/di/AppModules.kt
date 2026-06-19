package com.schuster.composecleanarchitecture.di

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.repository.PostRepositoryImpl
import com.schuster.composecleanarchitecture.data.retrofit.RetrofitService
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import com.schuster.composecleanarchitecture.domain.usecase.GetPostUseCase
import com.schuster.composecleanarchitecture.domain.usecase.GetPostUseCaseImpl
import com.schuster.composecleanarchitecture.presentation.feature.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModules = module {
    factory<GetPostUseCase> { GetPostUseCaseImpl(repository = get()) }
}

val presentationModules = module {
    viewModel { MainViewModel(useCase = get()) }
}

val dataModules = module {
    factory<PostRepository> { PostRepositoryImpl(api = get(), dispatcherIO = Dispatchers.IO) }
}

val networkModules = module {
    single { RetrofitService.create<PostApiService>() }
}

