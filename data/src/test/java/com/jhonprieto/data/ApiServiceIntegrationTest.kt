package com.jhonprieto.data

import com.jhonprieto.data.remote.ApiService
import com.jhonprieto.logger.Logger
import com.jhonprieto.network.BuildConfig
import com.jhonprieto.network.NetworkModule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ApiServiceIntegrationTest {

    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        Logger.init(BuildConfig.DEBUG)
        apiService = NetworkModule.createService<ApiService>(log = BuildConfig.DEBUG)
    }

    @Test
    fun `searchByQuery returns results for a valid term`() = runBlocking {
        val resp = apiService.searchByQuery(query = "ipad")
        assertNotNull(resp.results)
        assertTrue(resp.results.isNotEmpty())
    }

    @Test
    fun `searchByQuery returns empty for nonsense term`() = runBlocking {
        val resp = apiService.searchByQuery(query = "asdasdaksjd")
        assertNotNull(resp.results)
        assertTrue(resp.results.isEmpty())
    }

    @Test
    fun `searchByProductIdentifier returns result for a known GTIN`() = runBlocking {
        // sustituye por un GTIN válido en tu mercado local
        val resp = apiService.searchByProductIdentifier(productIdentifier = "7798410004649")
        assertNotNull(resp.results)
        assertTrue(resp.results.isNotEmpty())
        assertEquals("MLA16240160", resp.results.first().id) // ejemplo
    }

    @Test
    fun `searchByQueryAndDomain filters by domain`() = runBlocking {
        val resp = apiService.searchByQueryAndDomain(
            query = "Samsung",
            domainId = "MLA-TELEVISIONS"
        )
        assertNotNull(resp.results)
        // todos los resultados deben pertenecer al dominio MLA-TELEVISIONS
        assertTrue(resp.results.all { it.domainId == "MLA-TELEVISIONS" })
    }

    @Test
    fun `searchByAttributes returns results for brand Galaxy`() = runBlocking {
        val body = mapOf(
            "site_id" to "MLA",
            "domain_id" to "MLA-CELLPHONES",
            "status" to "active",
            "attributes" to listOf(
                mapOf("id" to "BRAND", "value_name" to "Samsung"),
                mapOf("id" to "LINE", "value_name" to "Galaxy")
            )
        )
        val resp = apiService.searchByAttributes(body)
        assertNotNull(resp.results)
        assertTrue(resp.results.isNotEmpty())
        assertTrue(
            resp.results.all {
                it.attributes.any { a -> a.id == "BRAND" && a.valueName == "Samsung" }
            }
        )
    }

    @Test
    fun `getProductDetail returns expected product`() = runBlocking {
        val detail = apiService.getProductDetail("MLA14719808")
        assertEquals("MLA14719808", detail.id)
        assertTrue(detail.name.isNotBlank())
    }
}
