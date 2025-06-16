package com.example.buved.domain.usecase.productId

import com.example.buved.domain.model.ProductId
import com.example.buved.domain.repository.ProductRepository

class DeleteProductIdUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(productId: ProductId) = repository.deleteProductId(productId)
}