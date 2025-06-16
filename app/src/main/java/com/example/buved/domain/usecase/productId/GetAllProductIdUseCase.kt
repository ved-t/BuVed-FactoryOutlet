package com.example.buved.domain.usecase.productId

import com.example.buved.domain.repository.ProductRepository

class GetAllProductIdUseCase(private val repository: ProductRepository) {
    operator fun invoke() = repository.getAllProductId()
}