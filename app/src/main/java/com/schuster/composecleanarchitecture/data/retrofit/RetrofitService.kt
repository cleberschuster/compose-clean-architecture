package com.schuster.composecleanarchitecture.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Esta classe tem a responsabilidade exclusiva de configurar e instanciar o cliente HTTP (OkHttpClient)
 * e o cliente REST (Retrofit). Ela expõe um único método de fábrica genérico [create] para instanciar
 * os serviços de API definidos no projeto.
 *
 * [SOLID: D - Dependency Inversion Principle (Princípio da Inversão de Dependência)]
 * Ao converter de `object` (singleton global) para uma classe normal, esta configuração
 * passa a ser gerenciada e provida pelo Koin (camada de DI). Isso elimina o acoplamento
 * rígido a um singleton global e permite substituir esta implementação por um mock/fake
 * em testes de integração sem nenhuma refatoração adicional.
 */
class RetrofitService {

    private fun criarHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @PublishedApi
    internal val retrofit: Retrofit = Retrofit.Builder()
        .client(criarHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}
