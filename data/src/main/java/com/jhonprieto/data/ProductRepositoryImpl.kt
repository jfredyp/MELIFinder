package com.jhonprieto.data

import com.jhonprieto.data.mapper.toDomain
import com.jhonprieto.data.remote.ApiService
import com.jhonprieto.data.remote.error.ApiErrorHandler
import com.jhonprieto.data.remote.error.ApiException
import com.jhonprieto.data.remote.error.toErrorType
import com.jhonprieto.domain.ApiResult
import com.jhonprieto.domain.model.Product
import com.jhonprieto.domain.model.ProductDetail
import com.jhonprieto.domain.repository.ProductRepository
import com.jhonprieto.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val apiService: ApiService
) : ProductRepository {

    override suspend fun searchByQuery(query: String): ApiResult<List<Product>> = withContext(Dispatchers.IO) {
        runCatching { apiService.searchByQuery(query = query) }
            .map { it.results.map { dto -> dto.toDomain() } }
            .fold(
                onSuccess = { products ->
                    if (products.isEmpty()) ApiResult.Empty else ApiResult.Success(products)
                },
                onFailure = { throwable ->
                    val apiEx = ApiErrorHandler.parse(throwable)
                    Logger.e("ProductRepositoryImpl", "Error in searchByQuery", apiEx)
                    ApiResult.Error(apiEx.toErrorType(), apiEx.message ?: apiEx.type.name)
                    /*when (apiEx) {
                        is ApiException.Network -> ApiResult.NetworkError
                        else -> ApiResult.Error(apiEx.toErrorType(), apiEx.message ?: apiEx.type.name)
                    }*/
                }
            )
    }

    override suspend fun getProductDetail(productId: String): ApiResult<ProductDetail> = withContext(Dispatchers.IO) {
        runCatching { apiService.getProductDetail(productId) }
            .map { dto -> dto.toDomain() }
            .fold(
                onSuccess = { detail -> ApiResult.Success(detail) },
                onFailure = { throwable ->
                    val apiEx = ApiErrorHandler.parse(throwable)
                    Logger.e("ProductRepositoryImpl", "Error in getProductDetail", apiEx)
                    when (apiEx) {
                        is ApiException.Network -> ApiResult.NetworkError
                        else -> ApiResult.Error(apiEx.toErrorType(), apiEx.message.orEmpty())
                    }
                }
            )
    }
}
