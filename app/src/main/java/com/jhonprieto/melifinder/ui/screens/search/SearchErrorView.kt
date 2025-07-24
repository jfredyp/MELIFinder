package com.jhonprieto.melifinder.ui.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jhonprieto.domain.error.ApiErrorType
import com.jhonprieto.melifinder.R
import com.jhonprieto.melifinder.ui.components.error.errorStateView

@Composable
fun searchErrorView(
    errorType: ApiErrorType,
    onRetry: () -> Unit
) {
    val (title, message) = when (errorType) {
        ApiErrorType.UNAUTHORIZED -> stringResource(R.string.unathorized) to stringResource(R.string.unathorized_desc)
        ApiErrorType.FORBIDDEN -> stringResource(R.string.FORBIDDEN) to stringResource(R.string.FORBIDDEN_DESC)
        ApiErrorType.NOT_FOUND -> stringResource(R.string.NOT_FOUND) to stringResource(R.string.NOT_FOUND_DESC)
        ApiErrorType.SERVER_ERROR -> stringResource(R.string.SERVER_ERROR) to stringResource(R.string.SERVER_ERROR_DESC)
        ApiErrorType.NETWORK -> stringResource(R.string.NETWORK) to stringResource(R.string.NETWORK_DESC)
        ApiErrorType.UNKNOWN -> stringResource(R.string.UNKNOWN) to stringResource(R.string.UNKNOWN_DESC)
    }
    errorStateView(
        title = title,
        message = message,
        onRetry = onRetry
    )
}
