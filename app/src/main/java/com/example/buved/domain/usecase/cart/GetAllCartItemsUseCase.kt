package com.example.buved.domain.usecase.cart

import com.example.buved.domain.model.CartItem
import com.example.buved.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllCartItemsUseCase(private val repository: ProductRepository) {
    operator fun invoke(): Flow<List<CartItem>> = repository.getAllCartItems()
}