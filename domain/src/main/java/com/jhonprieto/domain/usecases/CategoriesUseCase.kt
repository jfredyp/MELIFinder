package com.jhonprieto.domain.usecases

import com.jhonprieto.domain.model.Category
import com.jhonprieto.domain.model.CategoryDetail
import com.jhonprieto.domain.repository.CategoryRepository
import com.jhonprieto.domain.repository.ProductRepository

class GetCategoriesUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke() = repository.getCategories()
}

class GetCategoryDetailUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(categoryId: String): CategoryDetail = repository.getCategoryDetail(categoryId)
}
