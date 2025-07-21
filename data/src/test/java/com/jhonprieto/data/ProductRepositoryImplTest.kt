package com.jhonprieto.data

import com.jhonprieto.data.mock.MockApiService
import com.jhonprieto.data.remote.dto.PagingDto
import com.jhonprieto.data.remote.dto.ProductDetailDto
import com.jhonprieto.data.remote.dto.SearchResponseDto
import com.jhonprieto.domain.ApiResult
import com.jhonprieto.domain.error.ApiErrorType
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ProductRepositoryImplTest {
    @Test
    fun `searchByQuery success devuelve productos`() = runBlocking {
        val repo = ProductRepositoryImpl(MockApiService())
        val result = repo.searchByQuery("s8")
        assertTrue(result is ApiResult.Success)
        val list = (result as ApiResult.Success).data
        assertEquals(1, list.size)
        assertEquals("MOCK1", list.first().id)
    }

    @Test
    fun `searchByQuery empty devuelve lista vacía`() = runBlocking {
        val emptyService = object : MockApiService() {
            override suspend fun searchByQuery(status: String, siteId: String, query: String) =
                SearchResponseDto(
                    keywords = "",
                    paging = PagingDto(0, 0, 0),
                    results = emptyList()
                )
        }
        val result = ProductRepositoryImpl(emptyService).searchByQuery("nothing")
        assertTrue(result is ApiResult.Success)
        assertTrue((result as ApiResult.Success).data.isEmpty())
    }

    @Test
    fun `searchByQuery network error devuelve NETWORK`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun searchByQuery(status: String, siteId: String, query: String): SearchResponseDto {
                throw IOException("timeout")
            }
        }
        val result = ProductRepositoryImpl(service).searchByQuery("any")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.NETWORK, (result as ApiResult.Error).type)
    }

    @Test
    fun `searchByQuery unauthorized devuelve UNAUTHORIZED`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun searchByQuery(status: String, siteId: String, query: String): SearchResponseDto {
                val resp = Response.error<Any>(
                    401,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).searchByQuery("any")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.UNAUTHORIZED, (result as ApiResult.Error).type)
    }

    @Test
    fun `searchByQuery forbidden devuelve FORBIDDEN`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun searchByQuery(status: String, siteId: String, query: String): SearchResponseDto {
                val resp = Response.error<Any>(
                    403,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).searchByQuery("any")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.FORBIDDEN, (result as ApiResult.Error).type)
    }

    @Test
    fun `searchByQuery not found devuelve NOT_FOUND`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun searchByQuery(status: String, siteId: String, query: String): SearchResponseDto {
                val resp = Response.error<Any>(
                    404,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).searchByQuery("any")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.NOT_FOUND, (result as ApiResult.Error).type)
    }

    @Test
    fun `searchByQuery server error devuelve SERVER_ERROR`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun searchByQuery(status: String, siteId: String, query: String): SearchResponseDto {
                val resp = Response.error<Any>(
                    500,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).searchByQuery("any")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.SERVER_ERROR, (result as ApiResult.Error).type)
    }

    @Test
    fun `searchByQuery unknown http code devuelve UNKNOWN`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun searchByQuery(status: String, siteId: String, query: String): SearchResponseDto {
                val resp = Response.error<Any>(
                    418,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).searchByQuery("any")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.UNKNOWN, (result as ApiResult.Error).type)
    }

    @Test
    fun `searchByQuery generic exception devuelve UNKNOWN`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun searchByQuery(status: String, siteId: String, query: String): SearchResponseDto {
                throw error("boom")
            }
        }
        val result = ProductRepositoryImpl(service).searchByQuery("any")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.UNKNOWN, (result as ApiResult.Error).type)
    }

    //
    // getProductDetail
    //

    @Test
    fun `getProductDetail success devuelve detalle`() = runBlocking {
        val dto = ProductDetailDto(
            id = "X1",
            catalogProductId = null,
            status = "active",
            domainId = "MLA",
            name = "Mock Detail",
            familyName = null,
            type = null,
            permalink = null,
            mainFeatures = emptyList(),
            disclaimers = emptyList(),
            pictures = emptyList(),
            pickers = emptyList(),
            attributes = emptyList(),
            shortDescription = null
        )
        val service = object : MockApiService() {
            override suspend fun getProductDetail(productId: String) = dto
        }
        val result = ProductRepositoryImpl(service).getProductDetail("X1")
        assertTrue(result is ApiResult.Success)
        assertEquals("X1", (result as ApiResult.Success).data.id)
    }

    @Test
    fun `getProductDetail network error devuelve NETWORK`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun getProductDetail(productId: String) =
                throw IOException("timeout")
        }
        val result = ProductRepositoryImpl(service).getProductDetail("X1")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.NETWORK, (result as ApiResult.Error).type)
    }

    @Test
    fun `getProductDetail unauthorized devuelve UNAUTHORIZED`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun getProductDetail(productId: String): ProductDetailDto {
                val resp = Response.error<Any>(
                    401,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).getProductDetail("X1")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.UNAUTHORIZED, (result as ApiResult.Error).type)
    }

    @Test
    fun `getProductDetail forbidden devuelve FORBIDDEN`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun getProductDetail(productId: String): ProductDetailDto {
                val resp = Response.error<Any>(
                    403,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).getProductDetail("X1")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.FORBIDDEN, (result as ApiResult.Error).type)
    }

    @Test
    fun `getProductDetail not found devuelve NOT_FOUND`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun getProductDetail(productId: String): ProductDetailDto {
                val resp = Response.error<Any>(
                    404,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).getProductDetail("X1")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.NOT_FOUND, (result as ApiResult.Error).type)
    }

    @Test
    fun `getProductDetail server error devuelve SERVER_ERROR`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun getProductDetail(productId: String): ProductDetailDto {
                val resp = Response.error<Any>(
                    500,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).getProductDetail("X1")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.SERVER_ERROR, (result as ApiResult.Error).type)
    }

    @Test
    fun `getProductDetail unknown http code devuelve UNKNOWN`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun getProductDetail(productId: String): ProductDetailDto {
                val resp = Response.error<Any>(
                    418,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
                throw HttpException(resp)
            }
        }
        val result = ProductRepositoryImpl(service).getProductDetail("X1")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.UNKNOWN, (result as ApiResult.Error).type)
    }

    @Test
    fun `getProductDetail generic exception devuelve UNKNOWN`() = runBlocking {
        val service = object : MockApiService() {
            override suspend fun getProductDetail(productId: String): ProductDetailDto {
                throw error("boom")
            }
        }
        val result = ProductRepositoryImpl(service).getProductDetail("X1")
        assertTrue(result is ApiResult.Error)
        assertEquals(ApiErrorType.UNKNOWN, (result as ApiResult.Error).type)
    }
}
