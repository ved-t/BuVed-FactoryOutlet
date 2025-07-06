package com.example.buved.presentation.state.home

import com.example.buved.domain.model.CartItem
import com.example.buved.domain.model.Product

data class CartUiState(
    val isLoading: Boolean = true,
    val isBackgroundLoading: Boolean = true,

    val errorString: String? = null,

    val productList: List<Product> = emptyList(),
    val cartList: List<CartItem> = emptyList(),
    val totalItemCost: List<Double> = emptyList(),

    val totalAmount: String = "00.00",
    val totalItems: Int = 0,
    val totalUniqueItems: Int = 0,

    val deleteIconBadge: Int = 0
)
