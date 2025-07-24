package com.jhonprieto.data.remote.dto

import com.squareup.moshi.Json

data class CategoryDetailDto(
    val id: String,
    val name: String,
    val picture: String?,
    @Json(name = "children_categories")
    val childrenCategories: List<ChildCategoryDto>
)
