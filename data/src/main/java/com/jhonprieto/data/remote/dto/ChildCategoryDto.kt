package com.jhonprieto.data.remote.dto

import com.squareup.moshi.Json

data class ChildCategoryDto(
    val id: String,
    val name: String,
    @Json(name = "total_items_in_this_category")
    val totalItemsCategory: Int
)
