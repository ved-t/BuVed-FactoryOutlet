package com.example.buved.data.repository

import com.example.buved.data.remote.FakeStoreApi
import com.example.buved.data.remote.dto.ProductsDTOItem
import com.example.buved.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi
): ProductRepository {
    override suspend fun getProducts(): List<ProductsDTOItem>  = api.getProducts()

    override suspend fun getSingleProduct(productId: String): ProductsDTOItem = api.getSingleProduct(productId)
}

