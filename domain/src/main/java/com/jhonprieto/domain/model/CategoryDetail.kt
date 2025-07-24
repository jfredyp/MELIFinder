package com.jhonprieto.domain.model

data class CategoryDetail(
    val id: String,
    val name: String,
    val pictureUrl: String?, // Imagen de la categoría
    val children: List<ChildCategory>
)