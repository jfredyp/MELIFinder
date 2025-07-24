package com.jhonprieto.melifinder.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonprieto.domain.ApiResult
import com.jhonprieto.domain.error.ApiErrorType
import com.jhonprieto.domain.model.Product
import com.jhonprieto.domain.usecases.SearchProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val products: List<Product>) : SearchUiState()
    data class Error(val type: ApiErrorType, val message: String) : SearchUiState()
    object Empty : SearchUiState()
}

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState

    fun search(query: String) {
        if (query.isBlank()) {
            _uiState.value = SearchUiState.Idle
            return
        }
        _uiState.value = SearchUiState.Loading
        viewModelScope.launch {
            when (val result = searchProductsUseCase(query)) {
                is ApiResult.Success -> {
                    _uiState.value = if (result.data.isEmpty()) {
                        SearchUiState.Empty
                    } else {
                        SearchUiState.Success(result.data)
                    }
                }
                is ApiResult.Error -> {
                    _uiState.value = SearchUiState.Error(result.type, result.message)
                }
                is ApiResult.Empty -> _uiState.value = SearchUiState.Empty
                is ApiResult.NetworkError -> _uiState.value = SearchUiState.Empty
            }
        }
    }

    fun clear() {
        _uiState.value = SearchUiState.Idle
    }
}
