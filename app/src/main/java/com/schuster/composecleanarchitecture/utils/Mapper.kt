package com.schuster.composecleanarchitecture.utils

interface Mapper<S, T> {
    fun map(source: S): T
}