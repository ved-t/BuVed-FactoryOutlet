package com.example.buved.domain.usecase

import com.example.buved.data.remote.FakeStoreApi
import com.example.buved.data.remote.dto.ProductsDTOItem
import javax.inject.Inject

class GetSingleProductUseCase @Inject constructor(
   private val api: FakeStoreApi
) {
    suspend operator fun invoke(productId: String): ProductsDTOItem{
        return api.getSingleProduct(productId)
    }
}