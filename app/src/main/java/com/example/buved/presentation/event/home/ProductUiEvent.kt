package com.example.buved.presentation.event.home

import com.example.buved.domain.model.CartItem

sealed class ProductUiEvent {
    data class onAddToCart(val productId: Int): ProductUiEvent()
}