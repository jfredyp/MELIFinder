package com.jhonprieto.data.remote.error

import com.jhonprieto.domain.error.ApiErrorType

sealed class ApiException(
    val type: ApiErrorType,
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause) {
    class Unauthorized(cause: Throwable? = null) : ApiException(ApiErrorType.UNAUTHORIZED, cause = cause)
    class Forbidden(cause: Throwable? = null) : ApiException(ApiErrorType.FORBIDDEN, cause = cause)
    class NotFound(cause: Throwable? = null) : ApiException(ApiErrorType.NOT_FOUND, cause = cause)
    class ServerError(cause: Throwable? = null) : ApiException(ApiErrorType.SERVER_ERROR, cause = cause)
    class Network(cause: Throwable? = null) : ApiException(ApiErrorType.NETWORK, cause = cause)
    class Unknown(cause: Throwable? = null) : ApiException(ApiErrorType.UNKNOWN, cause = cause)
}

fun ApiException.toErrorType(): ApiErrorType = when (this) {
    is ApiException.Unauthorized -> ApiErrorType.UNAUTHORIZED
    is ApiException.Forbidden -> ApiErrorType.FORBIDDEN
    is ApiException.NotFound -> ApiErrorType.NOT_FOUND
    is ApiException.ServerError -> ApiErrorType.SERVER_ERROR
    is ApiException.Network -> ApiErrorType.NETWORK
    is ApiException.Unknown -> ApiErrorType.UNKNOWN
}
