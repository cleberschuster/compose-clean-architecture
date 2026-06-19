package com.schuster.composecleanarchitecture.utils

import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

fun handleApiError(exception: Throwable): String =
    when (exception) {
        is TimeoutException -> "Request timed out. Please try again."
        is IOException -> "Network error. Please check your connection."
        is HttpException -> {
            when (val statusCode = exception.code()) {
                400 -> "Bad Request"
                401 -> "Unauthorized. Please check your credentials."
                403 -> "Forbidden. Access is denied."
                404 -> "$statusCode recurso não encontrado, digite um ID válido"
                500 -> "Internal Server Error. Please try again later."
                503 -> "Service Unavailable. Please try again later."
                else -> "Unexpected HTTP Error: $statusCode"
            }
        }
        else -> "Ocorreu um erro inesperado: ${exception.message}"
    }
