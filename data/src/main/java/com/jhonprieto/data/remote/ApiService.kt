package com.jhonprieto.data.remote

import com.jhonprieto.data.remote.dto.ProductDetailDto
import com.jhonprieto.data.remote.dto.SearchResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /**
     * Búsqueda por palabra clave (búsqueda libre)
     * Ej: /products/search?q=notebook+dell
     */
    @GET("products/search")
    suspend fun searchByQuery(
        @Query("status") status: String = "active",
        @Query("site_id") siteId: String = "MCO",
        @Query("q") query: String,
        @Query("limit") limit: Int = 20
    ): SearchResponseDto

    /**
     * Búsqueda por identificador universal (GTIN, EAN, ISBN)
     * Ej: /products/search?product_identifier=7798410004649
     */
    @GET("products/search")
    suspend fun searchByProductIdentifier(
        @Query("status") status: String = "active",
        @Query("site_id") siteId: String = "MCO",
        @Query("product_identifier") productIdentifier: String,
        @Query("limit") limit: Int = 20
    ): SearchResponseDto

    /**
     * Búsqueda por dominio y palabra clave
     * Ej: /products/search?q=Samsung&domain_id=MLA-TELEVISIONS
     */
    @GET("products/search")
    suspend fun searchByQueryAndDomain(
        @Query("status") status: String = "active",
        @Query("site_id") siteId: String = "MCO",
        @Query("q") query: String,
        @Query("domain_id") domainId: String
    ): SearchResponseDto

    /**
     * Búsqueda avanzada por atributos
     * POST /products/search con cuerpo JSON
     */
    @POST("products/search")
    suspend fun searchByAttributes(@Body body: Map<String, Any>): SearchResponseDto

    /**
     * Obtener detalle completo de producto de catálogo
     * GET /products/{product_id}
     */
    @GET("products/{product_id}")
    suspend fun getProductDetail(
        @Path("product_id") productId: String
    ): ProductDetailDto
}
