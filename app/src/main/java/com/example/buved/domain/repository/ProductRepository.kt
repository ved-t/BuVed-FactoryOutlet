package com.example.buved.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.buved.data.local.CartItemEntity
import com.example.buved.data.local.ProductIdEntity
import com.example.buved.data.remote.dto.ProductsDTOItem
import com.example.buved.domain.model.CartItem
import com.example.buved.domain.model.ProductId
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

//    API GET requests
    suspend fun getProducts(): List<ProductsDTOItem>

    suspend fun getSingleProduct(productId: String): ProductsDTOItem


//    Product Id's CRUD
    suspend fun insertProductId(productId: ProductId)

    suspend fun deleteProductId(productId: ProductId)

    fun getAllProductId(): Flow<List<ProductId>>

//    Cart Item's CRUD
    suspend fun insertCartItem(cartItem: CartItem)

    suspend fun updateCartItem(cartItem: CartItem)

    suspend fun deleteCartItem(cartItem: CartItem)

    fun getAllCartItems(): Flow<List<CartItem>>
}
