package com.ranosan.wavies.core.data.repository

sealed class Resource<out R> {
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String? = null) : Resource<Nothing>()
}