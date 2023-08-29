package com.example.core_common.dto

import com.example.core_common.dto.dto.NetworkError


sealed class Result<out T : Any> {
    data class Success<T : Any>(val body: T) : Result<T>()
    data class Error(val error: NetworkError) : Result<Nothing>()
}
