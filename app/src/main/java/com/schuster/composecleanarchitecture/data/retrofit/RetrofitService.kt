package com.schuster.composecleanarchitecture.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

/**
 * [SOLID: S - Single Responsibility Principle (Princípio da Responsabilidade Única)]
 * Este objeto tem a responsabilidade exclusiva de configurar e instanciar o cliente HTTP (OkHttpClient)
 * e o cliente REST (Retrofit). Ele expõe um único método de fábrica genérico [create] para instanciar
 * os serviços de API definidos no projeto.
 */
object RetrofitService {

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

//    private fun aplicarToken(chain: Interceptor.Chain): Request {
//        return if (UsuarioLogado.isLogado()) {
//            val token = UsuarioLogado.token
//            chain.request()
//                .newBuilder()
//                .addHeader("Authorization", "${token.tipo} ${token.token}")
//                .build()
//        } else {
//            chain.request()
//        }
//    }

    val service: Retrofit = Retrofit.Builder()
        .client(criarHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> create(): T = service.create(T::class.java)
}
