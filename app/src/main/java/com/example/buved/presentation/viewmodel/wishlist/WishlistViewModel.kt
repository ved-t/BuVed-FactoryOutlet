package com.example.buved.presentation.viewmodel.wishlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.model.Product
import com.example.buved.domain.model.ProductId
import com.example.buved.domain.usecase.productId.DeleteProductIdUseCase
import com.example.buved.domain.usecase.productId.GetAllProductIdUseCase
import com.example.buved.domain.usecase.fakestoreapi.GetSingleProductUseCase
import com.example.buved.presentation.event.home.NavigationEvent
import com.example.buved.presentation.event.home.WishlistUiEvent
import com.example.buved.presentation.state.home.WishlistUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getAllProductIdUseCase: GetAllProductIdUseCase,
    private val getSingleProductUseCase: GetSingleProductUseCase,
    private val deleteProductIdUseCase: DeleteProductIdUseCase
) : ViewModel(){
    private val _uiState = MutableStateFlow(WishlistUiState())
    private val _productIdList = MutableStateFlow<List<ProductId>>(emptyList());
    private val _productList = MutableStateFlow<List<Product>>(emptyList());

    val uiState: StateFlow<WishlistUiState> = _uiState

    private val _navEvent = MutableSharedFlow<NavigationEvent>()
    val navEvent: SharedFlow<NavigationEvent> = _navEvent.asSharedFlow()

    init {
        fetchData()
    }

    private fun fetchData(){
        try{
            viewModelScope.launch {
                _productIdList.value = getAllProductIdUseCase().first()
                _productList.value = emptyList()

                _productIdList.value.forEach { it->
                    val product = getSingleProductUseCase(it.productId)
                    _productList.value += product
                }

                _productList.value = _productList.value.map {
                    it.copy(isFavourite = true)
                }
                _uiState.value = _uiState.value.copy(isLoading = false, list = _productList.value)
                Log.d("uiState", _uiState.value.toString())
            }
        }
        catch (e: Exception){
            _uiState.value = _uiState.value.copy(isLoading = false, errorString = e.message + "/nError fetching from DB")
            e.printStackTrace()
        }
    }

    fun onEvent(event: WishlistUiEvent){
        when(event){
            is WishlistUiEvent.onProductRemoveFavourite -> {
                removeFavourites(event.productId)
                onRefresh()
            }

            WishlistUiEvent.onRefresh -> onRefresh()
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

    private fun onRefresh(){
        fetchData()
    }

    private fun removeFavourites(productIdFromProduct: Int){
        val productId: ProductId = _productIdList.value.first {it->
            it.productId == productIdFromProduct.toString()
        }
        viewModelScope.launch {
            deleteProductIdUseCase(productId)
        }
    }
}