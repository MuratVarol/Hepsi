package com.varol.hepsi.remote

sealed class DataHolder<out T> {
    data class Success<out T>(val data: T) : DataHolder<T>()
    data class Error<out T>(val error: Throwable) : DataHolder<T>()
}