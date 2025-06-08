package com.example.buved.domain.repository

import com.example.buved.data.remote.dto.ProductsDTOItem

interface ProductRepository {
    suspend fun getProducts(): List<ProductsDTOItem>

    suspend fun getSingleProduct(productId: String): ProductsDTOItem
}
