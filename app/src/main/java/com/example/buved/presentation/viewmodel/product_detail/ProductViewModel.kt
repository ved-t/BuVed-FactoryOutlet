package com.example.buved.presentation.viewmodel.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.model.CartItem
import com.example.buved.domain.model.Product
import com.example.buved.domain.usecase.cart.InsertCartItemUseCase
import com.example.buved.domain.usecase.fakestoreapi.GetSingleProductUseCase
import com.example.buved.presentation.event.home.NavigationEvent
import com.example.buved.presentation.event.home.ProductUiEvent
import com.example.buved.presentation.state.home.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getSingleProductUseCase: GetSingleProductUseCase,
    private val insertCartItemUseCase: InsertCartItemUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    val productId: String = savedStateHandle["productId"] ?: "default"

    private val _productUiState = MutableStateFlow<ProductUiState>(ProductUiState())
    val productUiState: StateFlow<ProductUiState> = _productUiState

    private val _navEvent = MutableSharedFlow<NavigationEvent>()
    val navEvent: SharedFlow<NavigationEvent> = _navEvent.asSharedFlow()

    init {
        getSingleProduct()
    }

    private fun getSingleProduct(){
        try {
            viewModelScope.launch {
                val product: Product = getSingleProductUseCase(productId)
                _productUiState.value = _productUiState.value.copy(product = product, isLoading = false)
            }
        }
        catch (e: Exception){
            _productUiState.value = _productUiState.value.copy(isLoading = false, errorString = e.message)
            e.printStackTrace()
        }
    }

    fun onEvent(event: ProductUiEvent){
        when(event){
            is ProductUiEvent.onAddToCart -> addCartItem(event.productId)
        }
    }

    fun onNavEvent(event: NavigationEvent){
        when(event){
            is NavigationEvent.Navigate -> {
                viewModelScope.launch {
                    _navEvent.emit(event)
                }
            }

            NavigationEvent.PopBackStack -> {
                viewModelScope.launch {
                    _navEvent.emit(NavigationEvent.PopBackStack)
                }
            }
        }
    }

    private fun addCartItem(productId: Int){
        val cartItem = CartItem(
            productId = productId,
            productQuantity = 1
        )
        viewModelScope.launch {
            insertCartItemUseCase(cartItem)
        }
    }

}