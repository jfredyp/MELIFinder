package com.jhonprieto.domain.usecases

import com.jhonprieto.domain.model.Category
import com.jhonprieto.domain.model.CategoryDetail
import com.jhonprieto.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> = repository.getCategories()
}

class GetCategoryDetailUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(categoryId: String): CategoryDetail = repository.getCategoryDetail(categoryId)
}