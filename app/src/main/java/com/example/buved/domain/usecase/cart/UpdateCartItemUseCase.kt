package com.example.buved.domain.usecase.cart

import com.example.buved.domain.model.CartItem
import com.example.buved.domain.repository.ProductRepository

class UpdateCartItemUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(cartItem: CartItem) = repository.updateCartItem(cartItem)
}