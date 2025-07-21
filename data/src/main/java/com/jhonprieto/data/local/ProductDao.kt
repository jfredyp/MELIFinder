package com.jhonprieto.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getById(productId: String): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)
}
