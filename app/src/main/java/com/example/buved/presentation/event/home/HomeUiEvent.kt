package com.example.buved.presentation.event.home

import com.example.buved.presentation.FilterType

sealed class HomeUiEvent {
    data object onFilterClick: HomeUiEvent()
    data class onFilter(val filterType: FilterType): HomeUiEvent()
    data class onProductFavourite(val productId: String): HomeUiEvent()
    data class onProductRemoveFavourite(val productId: String): HomeUiEvent()
}