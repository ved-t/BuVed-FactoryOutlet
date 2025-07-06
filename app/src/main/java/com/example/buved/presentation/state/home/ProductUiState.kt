package com.example.buved.presentation.state.home

import com.example.buved.domain.model.Product

data class ProductUiState(
    val product: Product? = null,
    val isLoading: Boolean = true,
    val errorString: String? = null,

    val cartItemCount: String = "0"
)