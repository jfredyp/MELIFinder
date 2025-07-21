package com.jhonprieto.data.remote.dto

import com.squareup.moshi.Json

data class ProductDetailDto(
    val id: String,
    @Json(name = "catalog_product_id")
    val catalogProductId: String?,
    val status: String,
    @Json(name = "domain_id")
    val domainId: String,
    val name: String,
    @Json(name = "family_name")
    val familyName: String?,
    val type: String?,
    val permalink: String?,
    @Json(name = "main_features")
    val mainFeatures: List<FeatureDto> = emptyList(),
    val disclaimers: List<FeatureDto> = emptyList(),
    val pictures: List<PictureDto> = emptyList(),
    val pickers: List<PickerDto> = emptyList(),
    val attributes: List<AttributeDto> = emptyList(),
    @Json(name = "short_description")
    val shortDescription: ShortDescriptionDto? = null
)

data class FeatureDto(
    val text: String,
    val type: String
)

data class PictureDto(
    val id: String,
    val url: String
)

data class PickerDto(
    @Json(name = "picker_id")
    val pickerId: String,
    @Json(name = "picker_name")
    val pickerName: String,
    val products: List<PickerProductDto>,
    val attributes: List<PickerAttributeDto>
)

data class PickerProductDto(
    @Json(name = "product_id")
    val productId: String,
    @Json(name = "picker_label")
    val pickerLabel: String?,
    @Json(name = "picture_id")
    val pictureDd: String?,
    val thumbnail: String?,
    @Json(name = "product_name")
    val productName: String?
)

data class PickerAttributeDto(
    @Json(name = "attribute_id")
    val attributeId: String
)

data class ShortDescriptionDto(
    val type: String,
    val content: String
)
