package com.jhonprieto.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val raw: String // guarda JSON completo serializado si quieres simplificar el mapping
)
