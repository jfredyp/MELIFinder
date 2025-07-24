package com.jhonprieto.data.repositryImpl

import com.jhonprieto.data.mapper.toDomain
import com.jhonprieto.data.remote.ApiService
import com.jhonprieto.data.remote.error.ApiErrorHandler
import com.jhonprieto.data.remote.error.toErrorType
import com.jhonprieto.domain.ApiResult
import com.jhonprieto.domain.model.Category
import com.jhonprieto.domain.model.CategoryDetail
import com.jhonprieto.domain.repository.CategoryRepository
import com.jhonprieto.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(
    private val apiService: ApiService
) : CategoryRepository {
    private var categoriesCache: List<Category>? = null

    override suspend fun getCategories(): ApiResult<List<Category>> = withContext(Dispatchers.IO) {
        runCatching { apiService.getCategories() }
            .map { it.map { it.toDomain() } }
            .fold(
                onSuccess = { categories ->
                    categoriesCache = categories
                    if (categories.isEmpty()) ApiResult.Empty else ApiResult.Success(categories)
                },
                onFailure = { throwable ->
                    val apiEx = ApiErrorHandler.parse(throwable)
                    Logger.e("CategoryRepositoryImpl", "Error in getCategories", apiEx)
                    ApiResult.Error(apiEx.toErrorType(), apiEx.message ?: apiEx.type.name)
                }
            )
    }

    /*override suspend fun getCategories(): List<Category> {
        categoriesCache?.let {
            return it
        }
        val dtoList = apiService.getCategories()
        val result = dtoList.map { it.toDomain() }
        categoriesCache = result
        return result
    }*/

    override suspend fun getCategoryDetail(categoryId: String): CategoryDetail {
        val dto = apiService.getCategoryDetail(categoryId)
        return dto.toDomain()
    }
}
