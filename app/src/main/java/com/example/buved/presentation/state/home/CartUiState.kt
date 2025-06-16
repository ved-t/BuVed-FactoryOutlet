package com.example.buved.presentation.state.home

import com.example.buved.domain.model.Product

data class CartUiState(
    val isLoading: Boolean = true,
    val errorString: String? = null,
    val list: List<Product> = emptyList(),
    val totalAmount: String = "00.00",
    val totalItems: Int = 0,

    val deleteIconBadge: Int = 0
)
