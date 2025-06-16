package com.example.buved.presentation.state.home

import com.example.buved.domain.model.Product
import com.example.buved.presentation.FilterType

data class WishlistUiState (
    val list: List<Product> = emptyList(),
    val isLoading: Boolean = true,
    val errorString: String? = null
)