package com.example.buved.presentation.state.home

import com.example.buved.domain.model.Product
import com.example.buved.presentation.FilterType

data class HomeUiState(
    val filterString: String = FilterType.All.filterString,
    val filterType: FilterType = FilterType.All,
    val moreOptionsExpanded: Boolean = false,

    val list: List<Product> = emptyList(),
    val isLoading: Boolean = true,

    val favouriteCount: String = "0",
    val cartItemCount: String = "0"
)
