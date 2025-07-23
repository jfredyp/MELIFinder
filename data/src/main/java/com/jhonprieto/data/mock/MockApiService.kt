package com.jhonprieto.data.mock

import com.jhonprieto.data.remote.ApiService
import com.jhonprieto.data.remote.dto.AttributeDto
import com.jhonprieto.data.remote.dto.PagingDto
import com.jhonprieto.data.remote.dto.PictureDto
import com.jhonprieto.data.remote.dto.ProductDetailDto
import com.jhonprieto.data.remote.dto.ProductDto
import com.jhonprieto.data.remote.dto.SearchResponseDto

open class MockApiService : ApiService {
    override suspend fun searchByQuery(
        status: String,
        siteId: String,
        query: String,
        limit: Int
    ): SearchResponseDto {
        return SearchResponseDto(
            keywords = query,
            paging = PagingDto(total = 1, limit = 10, offset = 0),
            results = listOf(
                ProductDto(
                    id = "MOCK1",
                    name = "Samsung S8 Mock",
                    status = "active",
                    domainId = "MLA-CELLPHONES",
                    permalink = "http://mock-link",
                    attributes = listOf(
                        AttributeDto("PRICE", "Precio", null, "3000.0"),
                        AttributeDto("ITEM_CONDITION", "Condición", null, "new")
                    ),
                    pictures = listOf(
                        PictureDto(
                            id = "PIC1",
                            url = "https://via.placeholder.com/150"
                        )
                    )
                )
            )
        )
    }

    override suspend fun searchByProductIdentifier(
        status: String,
        siteId: String,
        productIdentifier: String,
        limit: Int
    ): SearchResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun searchByQueryAndDomain(
        status: String,
        siteId: String,
        query: String,
        domainId: String
    ): SearchResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun searchByAttributes(body: Map<String, Any>): SearchResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDetail(productId: String): ProductDetailDto {
        return ProductDetailDto(
            id = productId,
            catalogProductId = null,
            status = "active",
            domainId = "MLA-CELLPHONES",
            name = "Samsung S8 Mock Detail",
            familyName = null,
            type = null,
            permalink = "http://mock-detail",
            mainFeatures = emptyList(),
            disclaimers = emptyList(),
            pictures = emptyList(),
            pickers = emptyList(),
            attributes = listOf(
                AttributeDto("PRICE", "Precio", null, "3000.0"),
                AttributeDto("ITEM_CONDITION", "Condición", null, "new")
            ),
            shortDescription = null
        )
    }
}
