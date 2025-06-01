package com.example.buved.data.remote

import com.example.buved.data.remote.dto.ProductsDTOItem
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<ProductsDTOItem>

    @GET("products/{productId}")
    suspend fun getSingleProduct(@Path("productId") productId: String): ProductsDTOItem
}