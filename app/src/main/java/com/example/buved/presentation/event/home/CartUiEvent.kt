package com.example.buved.presentation.event.home

sealed class CartUiEvent {
    data class onItemIncrement(val productId: String): CartUiEvent()
    data class onItemDecrement(val productId: String): CartUiEvent()

    data object onDelete: CartUiEvent()
    data object onCheckout: CartUiEvent()
}