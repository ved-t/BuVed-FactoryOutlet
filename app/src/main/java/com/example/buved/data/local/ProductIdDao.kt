package com.example.buved.data.local

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductIdDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductId(productIdEntity: ProductIdEntity)

    @Delete
    suspend fun deleteProductId(productIdEntity: ProductIdEntity)

    @Query("SELECT * FROM productId ORDER BY id ASC")
    fun getAllProductId(): Flow<List<ProductIdEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItemEntity: CartItemEntity)

    @Update
    suspend fun updateCartItem(cartItemEntity: CartItemEntity)

    @Delete
    suspend fun deleteCartItem(cartItemEntity: CartItemEntity)

    @Query("SELECT * FROM cartItem ORDER BY id ASC")
    fun getAllCartItems(): Flow<List<CartItemEntity>>
}