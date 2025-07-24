/*package com.jhonprieto.melifinder.ui.screens.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonprieto.domain.ApiResult
import com.jhonprieto.domain.SearchProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val searchProductDetailsUseCase: SearchProductDetailsUseCase

) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    fun loadDetail(productId: String) {
        viewModelScope.launch {
            _uiState.value = ProductDetailUiState.Loading
            if (productId.isBlank()) {
                _uiState.value = ProductDetailUiState.Error("Product ID cannot be empty")
                return@launch
            }

            try {
                when (val result = searchProductDetailsUseCase(productId)) {
                    is ApiResult.Success -> _uiState.value = ProductDetailUiState.Success(result.data)
                    is ApiResult.Error -> _uiState.value = ProductDetailUiState.Error(result.message)
                    is ApiResult.NetworkError -> _uiState.value = ProductDetailUiState.Error("Network error")
                    is ApiResult.Empty -> _uiState.value = ProductDetailUiState.Error("No data found")
                }
            } catch (e: Exception) {
                _uiState.value = ProductDetailUiState.Error("Error loading product details")
            }
        }
    }
}*/

package com.jhonprieto.melifinder.ui.screens.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonprieto.domain.ApiResult
import com.jhonprieto.domain.usecases.SearchProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val searchProductDetailsUseCase: SearchProductDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    fun loadDetail(productId: String) {
        viewModelScope.launch {
            _uiState.value = ProductDetailUiState.Loading
            if (productId.isBlank()) {
                _uiState.value = ProductDetailUiState.Error("Product ID cannot be empty")
                return@launch
            } else {
                when (val result = searchProductDetailsUseCase(productId)) {
                    is ApiResult.Success -> _uiState.value = ProductDetailUiState.Success(result.data)
                    is ApiResult.Error -> _uiState.value = ProductDetailUiState.Error(result.message)
                    is ApiResult.NetworkError -> _uiState.value = ProductDetailUiState.Error("Network error")
                    is ApiResult.Empty -> _uiState.value = ProductDetailUiState.Error("No data found")
                }
            }
        }
    }
}
