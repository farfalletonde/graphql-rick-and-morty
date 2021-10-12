package com.example.rickandmorty.util

sealed class StateResource<out T> {
    data class Success<T>(val data: T) : StateResource<T>()
    data class Error<T>(val e: Exception) : StateResource<T>()
    data class Loading<T>(val data: T? = null) : StateResource<T>()
}