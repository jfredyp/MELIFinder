package com.jhonprieto.data.mapper

import com.jhonprieto.data.remote.dto.CategoryDetailDto
import com.jhonprieto.data.remote.dto.CategoryDto
import com.jhonprieto.data.remote.dto.ChildCategoryDto
import com.jhonprieto.domain.model.Category
import com.jhonprieto.domain.model.CategoryDetail
import com.jhonprieto.domain.model.ChildCategory

fun CategoryDto.toDomain() = Category(
    id = id,
    name = name,
    picture = null
)

fun CategoryDetailDto.toDomain() = CategoryDetail(
    id = id,
    name = name,
    pictureUrl = picture,
    children = childrenCategories.map { it.toDomain() }
)

fun ChildCategoryDto.toDomain() = ChildCategory(
    id = id,
    name = name,
    totalItems = totalItemsCategory
)
