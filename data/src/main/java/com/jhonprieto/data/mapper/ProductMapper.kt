package com.jhonprieto.data.mapper

import com.jhonprieto.common.MoshiProvider
import com.jhonprieto.data.local.ProductEntity
import com.jhonprieto.data.remote.dto.ProductDto
import com.jhonprieto.domain.model.Product

private val moshi = MoshiProvider.moshi
private val adapter = moshi.adapter(ProductDto::class.java)

fun ProductDto.toEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    raw = adapter.toJson(this)
)

// Extension: ProductEntity -> ProductDto (para reconstruir el dto desde cache/local)
fun ProductEntity.toProductDto(): ProductDto =
    adapter.fromJson(raw) ?: error("Fallo al deserializar ProductDto desde cache")

// Extension: ProductEntity -> Product (modelo de dominio final)
fun ProductEntity.toDomain(): Product = this.toProductDto().toDomain()

// Extension: ProductDto -> Product (modelo de dominio final)
fun ProductDto.toDomain(): Product = Product(
    id = id,
    title = name,
    price = attributes.find { it.id == "PRICE" }?.valueName?.takeIf { !it.isNullOrBlank() }?.toDoubleOrNull(),
    thumbnail = null,
    mainImageUrl = pictures?.firstOrNull()?.url, // <- obtiene la primera imagen, si hay
    allImageUrls = pictures?.map { it.url } ?: emptyList(),
    condition = attributes.find { it.id == "ITEM_CONDITION" }?.valueName,
    permalink = permalink
)
