package com.schuster.composecleanarchitecture.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Esta classe tem a única responsabilidade de inicializar o contêiner de injeção de dependências (Koin)
 * com o escopo e contexto do aplicativo Android.
 */
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(domainModules)
            modules(dataModules)
            modules(presentationModules)
            modules(networkModules)
        }
    }
}