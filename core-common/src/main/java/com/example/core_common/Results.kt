package com.example.core_common

import com.example.core_common.dto.NetworkError


sealed class Results<out T : Any> {
    data class Success<T : Any>(val body: T) : Results<T>()
    data class Error(val error: NetworkError) : Results<Nothing>()
}
