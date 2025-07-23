package com.jhonprieto.domain.model

data class Product(
    val id: String,
    val title: String,
    val price: Double?,
    val mainImageUrl: String?,
    val allImageUrls: List<String> = emptyList(),
    val thumbnail: String?,
    val condition: String?,
    val permalink: String?
)



