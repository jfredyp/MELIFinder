package com.jhonprieto.data.remote.dto

data class SearchResponseDto(
    val keywords: String?,
    val paging: PagingDto,
    val results: List<ProductDto>
)

data class PagingDto(
    val total: Int,
    val offset: Int,
    val limit: Int
)
