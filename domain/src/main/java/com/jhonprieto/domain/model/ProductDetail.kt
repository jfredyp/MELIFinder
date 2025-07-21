package com.jhonprieto.domain.model

data class ProductDetail(
    val id: String,
    val name: String,
    val status: String,
    val familyName: String?,
    val domainId: String,
    val permalink: String?,
    val pictures: List<String>,
    val mainFeatures: List<String>,
    val shortDescription: String?,
    val attributes: Map<String, String>,
    val availableVariants: List<Map<String, String>>
)
