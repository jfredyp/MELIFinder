package com.jhonprieto.search

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Product>>>(UiState.Empty)
    val state: StateFlow<UiState<List<Product>>> = _state

    fun search(query: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val result = searchProductsUseCase(query)
                _state.value = if (result.isEmpty()) UiState.Empty else UiState.Success(result)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
