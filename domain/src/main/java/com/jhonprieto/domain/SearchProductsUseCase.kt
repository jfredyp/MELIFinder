package com.jhonprieto.domain

import com.jhonprieto.domain.repository.ProductRepository

class SearchProductsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(query: String) = repository.searchByQuery(query)
}

class SearchProductDetailsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(productId: String) = repository.getProductDetail(productId)
}
