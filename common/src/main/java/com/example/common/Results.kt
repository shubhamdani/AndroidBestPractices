package com.example.common

import com.example.common.dto.NetworkError


sealed class Results<out T : Any> {
    data class Success<T : Any>(val body: T) : Results<T>()
    data class Error(val error: NetworkError) : Results<Nothing>()
}
