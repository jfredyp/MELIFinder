package com.jhonprieto.data.repositryImpl

import com.jhonprieto.data.mapper.toDomain
import com.jhonprieto.data.remote.ApiService
import com.jhonprieto.domain.model.Category
import com.jhonprieto.domain.model.CategoryDetail
import com.jhonprieto.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val apiService: ApiService
) : CategoryRepository {
    private var categoriesCache: List<Category>? = null

    override suspend fun getCategories(): List<Category> {
        categoriesCache?.let {
            return it
        }
        val dtoList = apiService.getCategories()
        val result = dtoList.map { it.toDomain() }
        categoriesCache = result
        return result
    }

    override suspend fun getCategoryDetail(categoryId: String): CategoryDetail {
        val dto = apiService.getCategoryDetail(categoryId)
        return dto.toDomain()
    }
}
