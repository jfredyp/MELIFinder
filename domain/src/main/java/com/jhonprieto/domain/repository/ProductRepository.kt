package com.jhonprieto.domain.repository

import com.jhonprieto.domain.ApiResult
import com.jhonprieto.domain.model.Product
import com.jhonprieto.domain.model.ProductDetail

interface ProductRepository {
    suspend fun searchByQuery(query: String): ApiResult<List<Product>>
    suspend fun getProductDetail(productId: String): ApiResult<ProductDetail>
}
