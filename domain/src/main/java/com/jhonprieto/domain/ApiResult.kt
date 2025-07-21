package com.jhonprieto.domain

import com.jhonprieto.domain.error.ApiErrorType

sealed class ApiResult<out T> {
    data class Success<T>(val data: T, val fromCache: Boolean = false) : ApiResult<T>()
    data class Error(
        val type: ApiErrorType,
        val message: String,
        val cause: Throwable? = null
    ) : ApiResult<Nothing>()
    object NetworkError : ApiResult<Nothing>()
    object Empty : ApiResult<Nothing>()
}
