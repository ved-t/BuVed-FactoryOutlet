package com.example.buved.presentation.viewmodel.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.model.CartItem
import com.example.buved.domain.model.Product
import com.example.buved.domain.usecase.cart.DeleteCartItemUseCase
import com.example.buved.domain.usecase.cart.GetAllCartItemsUseCase
import com.example.buved.domain.usecase.cart.UpdateCartItemUseCase
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
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllCartItemsUseCase: GetAllCartItemsUseCase,
    private val getSingleProductUseCase: GetSingleProductUseCase,
    private val updateCartItemUseCase: UpdateCartItemUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(CartUiState())
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    private val _totalItemCost = MutableStateFlow<List<Double>>(emptyList())

    private val _totalItems = MutableStateFlow<Int>(0)
    private val _totalCost = MutableStateFlow<Double>(0.0)

    val uiState: StateFlow<CartUiState> = combine(_uiState, _cartItems){uiState, cartItems ->
        uiState.copy(
            totalUniqueItems = cartItems.size,
            totalItems = _totalItems.value
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
                _totalItemCost.value = emptyList()
                _totalItems.value = 0
                _totalCost.value = 0.0

                _cartItems.value.forEach { it->
                    val productId: String = it.productId.toString()
                    val product = getSingleProductUseCase(productId)
                    _productList.value += product
                    _totalItemCost.value += (product.price * it.productQuantity)

                    _totalItems.value += it.productQuantity
                    _totalCost.value += (product.price * it.productQuantity)
                }

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorString = null,
                    productList = _productList.value,
                    cartList = _cartItems.value,
                    totalItemCost = _totalItemCost.value,
                    totalAmount = String.format(Locale.ENGLISH, "%.2f", _totalCost.value),
                    isBackgroundLoading = false
                )
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

            is CartUiEvent.onItemDecrement -> {
                onDecrement(event.cartItem)
                onRefresh()
            }

            is CartUiEvent.onItemIncrement -> {
                onIncrement(event.cartItem)
                onRefresh()
            }
        }
    }

    private fun onRefresh(){
        _uiState.value = _uiState.value.copy(isBackgroundLoading = true)
        fetchData()
    }

    private fun onIncrement(cartItem: CartItem){
        val cartItemQuantity = cartItem.productQuantity + 1
        if(cartItemQuantity >= 8){
            return
        }
        val newCartItem = cartItem.copy(
            productQuantity = cartItemQuantity
        )
        viewModelScope.launch {
            updateCartItemUseCase(newCartItem)
        }
    }

    private fun onDecrement(cartItem: CartItem){
        val cartItemQuantity = cartItem.productQuantity - 1
        if(cartItemQuantity <= 0){
            viewModelScope.launch {
                deleteCartItemUseCase(cartItem)
            }
            return
        }
        val newCartItem = cartItem.copy(
            productQuantity = cartItemQuantity
        )
        viewModelScope.launch {
            updateCartItemUseCase(newCartItem)
        }
    }
}