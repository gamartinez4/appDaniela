package com.example.appdaniela.models

sealed class Results<out T> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Failure<out T>(val exception: Exception) : Results<T>()
}