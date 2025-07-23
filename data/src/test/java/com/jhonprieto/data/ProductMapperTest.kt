package com.jhonprieto.data

import com.jhonprieto.data.mapper.toDomain
import com.jhonprieto.data.remote.dto.AttributeDto
import com.jhonprieto.data.remote.dto.ProductDto
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductMapperTest {

    @Test
    fun `dtoToDomain mapea correctamente todos los campos`() {
        val dto = ProductDto(
            id = "ID1",
            name = "Samsung Galaxy",
            status = "active",
            domainId = "MLA-CELLPHONES",
            permalink = "http://permalink",
            attributes = listOf(
                AttributeDto("PRICE", "Precio", null, "5000.0"),
                AttributeDto("ITEM_CONDITION", "Condición", null, "new")
            ),
            pictures = emptyList()
        )

        val domain = dto.toDomain()
        assertEquals("ID1", domain.id)
        assertEquals("Samsung Galaxy", domain.title)
        domain.price?.let { assertEquals(5000.0, it, 0.01) }
        assertEquals("new", domain.condition)
        assertEquals("http://permalink", domain.permalink)
        assertEquals(null, domain.thumbnail)
    }

    @Test
    fun `dtoToDomain maneja atributos nulos o faltantes correctamente`() {
        val dto = ProductDto(
            id = "ID2",
            name = "Producto sin precio",
            status = "active",
            domainId = "MLA-CELLPHONES",
            permalink = null,
            attributes = emptyList(),
            pictures = emptyList()
        )

        val domain = dto.toDomain()
        assertEquals("ID2", domain.id)
        assertEquals("Producto sin precio", domain.title)
        assertEquals(null, domain.price)
        assertEquals(null, domain.condition)
        assertEquals(null, domain.thumbnail)
        assertEquals(null, domain.permalink)
    }
}
