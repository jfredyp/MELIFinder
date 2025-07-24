package com.jhonprieto.melifinder.ui.screens.productDetail

import com.jhonprieto.domain.model.ProductDetail

sealed class ProductDetailUiState {
    object Loading : ProductDetailUiState()
    data class Success(val detail: ProductDetail) : ProductDetailUiState()
    data class Error(val message: String) : ProductDetailUiState()
}
