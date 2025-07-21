package com.jhonprieto.data.remote.error

import retrofit2.HttpException
import java.io.IOException

private const val HTTP_UNAUTHORIZED = 401
private const val HTTP_FORBIDDEN = 403
private const val HTTP_NOT_FOUND = 404
private const val HTTP_SERVER_ERROR_START = 500
private const val HTTP_SERVER_ERROR_END = 599

object ApiErrorHandler {
    fun parse(throwable: Throwable): ApiException = when (throwable) {
        is HttpException -> when (val code = throwable.code()) {
            HTTP_UNAUTHORIZED -> ApiException.Unauthorized(throwable)
            HTTP_FORBIDDEN -> ApiException.Forbidden(throwable)
            HTTP_NOT_FOUND -> ApiException.NotFound(throwable)
            in HTTP_SERVER_ERROR_START..HTTP_SERVER_ERROR_END -> ApiException.ServerError(throwable)
            else -> ApiException.Unknown(throwable)
        }
        is IOException -> ApiException.Network(throwable)
        else -> ApiException.Unknown(throwable)
    }
}
