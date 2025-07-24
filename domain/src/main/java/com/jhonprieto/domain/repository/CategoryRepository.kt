package com.jhonprieto.domain.repository

import com.jhonprieto.domain.model.Category
import com.jhonprieto.domain.model.CategoryDetail

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getCategoryDetail(categoryId: String): CategoryDetail
}