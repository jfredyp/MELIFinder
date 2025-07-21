package com.jhonprieto.data

import com.jhonprieto.domain.model.Product

data class SearchResponse(
    val results: List<Product>
)
