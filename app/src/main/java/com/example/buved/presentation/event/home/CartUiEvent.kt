package com.example.buved.presentation.event.home

import com.example.buved.domain.model.CartItem

sealed class CartUiEvent {
    data class onItemIncrement(val cartItem: CartItem): CartUiEvent()
    data class onItemDecrement(val cartItem: CartItem): CartUiEvent()

    data object onDelete: CartUiEvent()
    data object onCheckout: CartUiEvent()
}