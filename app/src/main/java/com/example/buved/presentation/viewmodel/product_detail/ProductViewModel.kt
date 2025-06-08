package com.example.buved.presentation.viewmodel.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel(){
    val productId: String = savedStateHandle["productId"] ?: "default"
}