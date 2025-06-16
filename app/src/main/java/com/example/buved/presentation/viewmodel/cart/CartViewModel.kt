package com.example.buved.presentation.viewmodel.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.model.CartItem
import com.example.buved.domain.model.Product
import com.example.buved.domain.usecase.cart.GetAllCartItemsUseCase
import com.example.buved.domain.usecase.cart.InsertCartItemUseCase
import com.example.buved.domain.usecase.fakestoreapi.GetSingleProductUseCase
import com.example.buved.presentation.event.home.CartUiEvent
import com.example.buved.presentation.state.home.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllCartItemsUseCase: GetAllCartItemsUseCase,
    private val getSingleProductUseCase: GetSingleProductUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(CartUiState())
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    private val _productList = MutableStateFlow<List<Product>>(emptyList())

    val uiState: StateFlow<CartUiState> = combine(_uiState, _cartItems){uiState, cartItems ->
        uiState.copy(
            totalItems = cartItems.size
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        CartUiState()
    )

    init {
        fetchData()
    }

    private fun fetchData(){
        try {
            viewModelScope.launch {
                _cartItems.value = getAllCartItemsUseCase().first()
                _productList.value = emptyList()

                _cartItems.value.forEach { it->
                    val productId: String = it.productId.toString()
                    val product = getSingleProductUseCase(productId)
                    _productList.value += product
                }

                _uiState.value = _uiState.value.copy(isLoading = false, errorString = null, list = _productList.value)
                Log.d("uiState", _uiState.value.toString())
            }
        }
        catch (e: Exception){
            _uiState.value = _uiState.value.copy(errorString = e.toString())
            e.printStackTrace()
        }
    }

    fun onEvent(event: CartUiEvent){
        when(event){
            CartUiEvent.onCheckout -> {}
            CartUiEvent.onDelete -> {}
            is CartUiEvent.onItemDecrement -> {}
            is CartUiEvent.onItemIncrement -> {}
        }
    }

    private fun onRefresh(){
        fetchData()
    }
}