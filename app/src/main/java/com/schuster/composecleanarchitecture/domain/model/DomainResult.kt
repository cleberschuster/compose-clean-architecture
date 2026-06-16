package com.schuster.composecleanarchitecture.domain.model

sealed interface DomainResult<out T> {
    data class Success<out T>(val data: T) : DomainResult<T>
    data class Error(val message: String) : DomainResult<Nothing>
}
