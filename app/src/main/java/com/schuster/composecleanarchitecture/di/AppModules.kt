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

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Cada variável de módulo Koin possui a responsabilidade única de declarar os objetos e dependências
 * associados exclusivamente à sua camada arquitetural específica (domain, presentation, data, network).
 *
 * [SOLID: I - Interface Segregation Principle (Princípio da Segregação de Interfaces)]
 * Segregamos a declaração dos módulos de dependências por camada em vez de declarar tudo em um módulo
 * monolítico. Isso melhora a legibilidade, facilita a modularização no futuro e o controle de dependências.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * O Koin realiza a injeção automática de dependências, permitindo registrar implementações concretas
 * associadas a tipos abstratos de interfaces (ex. [GetPostUseCase] ligado a [GetPostUseCaseImpl], 
 * e [PostRepository] a [PostRepositoryImpl]). Isso garante que o sistema dependa de abstrações e não
 * de implementações específicas.
 */

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

