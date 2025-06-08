package com.example.buved.domain.usecase

import com.example.buved.data.remote.dto.toDomain
import com.example.buved.domain.model.Product
import com.example.buved.domain.repository.ProductRepository
import javax.inject.Inject

class GetSingleProductUseCase @Inject constructor(
   private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Product{
        try {
            return productRepository.getSingleProduct(productId).toDomain()
        }catch (e: Exception){
            throw e
        }
    }
}