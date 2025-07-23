package com.jhonprieto.melifinder.ui.screens.productDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun productDetailScreen(
    productId: String,
    viewModel: ProductDetailViewModel = koinViewModel(),
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(productId) { viewModel.loadDetail(productId) }

    when (val state = uiState) {
        is ProductDetailUiState.Loading -> detailLoadingView()
        is ProductDetailUiState.Error -> detailErrorView(message = state.message)
        is ProductDetailUiState.Success -> productDetailContent(state.detail, onBack)
    }
}
