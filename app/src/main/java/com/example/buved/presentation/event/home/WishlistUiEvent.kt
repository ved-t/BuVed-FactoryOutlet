package com.example.buved.presentation.event.home

import com.example.buved.domain.model.ProductId

sealed class WishlistUiEvent {
    data class onProductRemoveFavourite(val productId: Int): WishlistUiEvent()
    data object onRefresh: WishlistUiEvent()
}