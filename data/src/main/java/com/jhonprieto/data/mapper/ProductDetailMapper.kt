package com.jhonprieto.data.mapper

import com.jhonprieto.data.remote.dto.ProductDetailDto
import com.jhonprieto.domain.model.ProductDetail

fun ProductDetailDto.toDomain(): ProductDetail {
    return ProductDetail(
        id = id,
        name = name,
        status = status,
        familyName = familyName,
        domainId = domainId,
        permalink = permalink,
        pictures = pictures.map { it.url },
        mainFeatures = mainFeatures.map { it.text },
        shortDescription = shortDescription?.content,
        attributes = attributes.associate { it.name to (it.valueName ?: "") },
        availableVariants = pickers.flatMap { picker ->
            picker.products.map { product ->
                mapOf(
                    "picker" to picker.pickerName,
                    "value" to (product.pickerLabel ?: ""),
                    "image" to (product.thumbnail ?: "")
                )
            }
        }
    )
}
