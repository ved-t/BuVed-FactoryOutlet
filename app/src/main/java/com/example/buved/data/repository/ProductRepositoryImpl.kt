package com.example.buved.data.repository

import android.util.Log
import com.example.buved.data.local.ProductIdDao
import com.example.buved.data.local.ProductIdEntity
import com.example.buved.data.local.toDomain
import com.example.buved.data.local.toEntity
import com.example.buved.data.remote.FakeStoreApi
import com.example.buved.data.remote.dto.ProductsDTOItem
import com.example.buved.domain.model.CartItem
import com.example.buved.domain.model.ProductId
import com.example.buved.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi,
    private val productIdDao: ProductIdDao
): ProductRepository {
//    Api GET requests
    override suspend fun getProducts(): List<ProductsDTOItem>  = api.getProducts()

    override suspend fun getSingleProduct(productId: String): ProductsDTOItem = api.getSingleProduct(productId)

//    Product Id's CRUD
    override suspend fun insertProductId(productId: ProductId) {
        Log.d("dbInsert", productId.toString())
        productIdDao.insertProductId(productId.toEntity())
    }

    override suspend fun deleteProductId(productId: ProductId) {
        Log.d("dbDelete", productId.toString())
        productIdDao.deleteProductId(productId.toEntity())
    }

    override fun getAllProductId(): Flow<List<ProductId>> = productIdDao.getAllProductId().map { list -> list.map { it-> it.toDomain() } }

//    Cart Item's CRUD
    override suspend fun insertCartItem(cartItem: CartItem) {
    Log.d("dbInsert", cartItem.toString())
        productIdDao.insertCartItem(cartItem.toEntity())
    }

    override suspend fun updateCartItem(cartItem: CartItem) {
        Log.d("dbUpdate", cartItem.toString())
        productIdDao.updateCartItem(cartItem.toEntity())
    }

    override suspend fun deleteCartItem(cartItem: CartItem) {
        Log.d("dbDelete", cartItem.toString())
        productIdDao.deleteCartItem(cartItem.toEntity())
    }

    override fun getAllCartItems(): Flow<List<CartItem>> {
        return productIdDao.getAllCartItems().map { list -> list.map { it.toDomain() } }
    }
}

