package com.jhonprieto.melifinder.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonprieto.domain.ApiResult
import com.jhonprieto.domain.error.ApiErrorType
import com.jhonprieto.domain.model.Category
import com.jhonprieto.domain.usecases.GetCategoriesUseCase
import com.jhonprieto.domain.usecases.GetCategoryDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    data class Success(val categories: List<Category>) : CategoryUiState()
    data class Error(val type: ApiErrorType, val message: String) : CategoryUiState()
}

class CategoryViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCategoryDetailUseCase: GetCategoryDetailUseCase
) : ViewModel() {

    // private val _categories = MutableStateFlow<List<Category>>(emptyList())
    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState

    private val pictureCache = mutableMapOf<String, String?>()

    fun loadCategories() {
        _uiState.value = CategoryUiState.Loading
        viewModelScope.launch {
            when (val result = getCategoriesUseCase()) {
                is ApiResult.Success -> {
                    val categories = result.data
                    if (categories.isEmpty()) {
                        _uiState.value = CategoryUiState.Success(emptyList())
                        return@launch
                    }

                    _uiState.value = CategoryUiState.Success(categories)

                    categories.forEach { category ->
                        if (pictureCache[category.id] == null) {
                            launch {
                                val detail = getCategoryDetailUseCase(category.id)
                                pictureCache[category.id] = detail.pictureUrl
                                val updated = (uiState.value as? CategoryUiState.Success)
                                    ?.categories
                                    ?.map {
                                        if (it.id == category.id) {
                                            it.copy(picture = detail.pictureUrl)
                                        } else {
                                            it
                                        }
                                    } ?: emptyList()
                                _uiState.value = CategoryUiState.Success(updated)
                            }
                        } else {
                            val updated = (uiState.value as? CategoryUiState.Success)
                                ?.categories
                                ?.map {
                                    if (it.id == category.id) {
                                        it.copy(picture = pictureCache[category.id])
                                    } else {
                                        it
                                    }
                                } ?: emptyList()
                            _uiState.value = CategoryUiState.Success(updated)
                        }
                    }
                }
                is ApiResult.Error -> {
                    _uiState.value = CategoryUiState.Error(result.type, result.message)
                }

                is ApiResult.Empty -> {
                    _uiState.value = CategoryUiState.Success(emptyList())
                }

                is ApiResult.NetworkError -> {
                    _uiState.value = CategoryUiState.Error(
                        ApiErrorType.NETWORK,
                        "No internet connection"
                    )
                }
            }
        }
    }
}
