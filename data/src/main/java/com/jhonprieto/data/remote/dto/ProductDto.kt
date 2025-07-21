package com.jhonprieto.data.remote.dto

import com.squareup.moshi.Json

data class ProductDto(
    val id: String,
    val name: String,
    val status: String,
    @Json(name = "domain_id")
    val domainId: String,
    val permalink: String?,
    val attributes: List<AttributeDto> = emptyList()
)

data class AttributeDto(
    val id: String,
    val name: String,
    @Json(name = "value_id")
    val valueId: String?,
    @Json(name = "value_name")
    val valueName: String?
)
