package com.jhonprieto.melifinder.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhonprieto.domain.model.Category
import com.jhonprieto.domain.usecases.GetCategoriesUseCase
import com.jhonprieto.domain.usecases.GetCategoryDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    data class Success(val categories: List<Category>) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}

class CategoryViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCategoryDetailUseCase: GetCategoryDetailUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val pictureCache = mutableMapOf<String, String?>()

    fun loadCategories() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val cats = getCategoriesUseCase()
                _categories.value = cats

                cats.forEach { category ->
                    if (pictureCache[category.id] == null) {
                        launch {
                            val detail = getCategoryDetailUseCase(category.id)
                            pictureCache[category.id] = detail.pictureUrl
                            _categories.update { list ->
                                list.map {
                                    if (it.id == category.id) it.copy(picture = detail.pictureUrl) else it
                                }
                            }
                            // *** Aquí fuerza también el UI State ***
                            _uiState.value = CategoryUiState.Success(_categories.value)
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                _error.value = "No internet connection"
            }
            _loading.value = false
        }
    }
}
