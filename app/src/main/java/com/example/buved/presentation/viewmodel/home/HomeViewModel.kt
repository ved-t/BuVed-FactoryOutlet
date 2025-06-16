package com.example.buved.presentation.viewmodel.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.model.Product
import com.example.buved.domain.model.ProductId
import com.example.buved.domain.usecase.productId.DeleteProductIdUseCase
import com.example.buved.domain.usecase.productId.GetAllProductIdUseCase
import com.example.buved.domain.usecase.fakestoreapi.GetProductsUseCase
import com.example.buved.domain.usecase.productId.InsertProductIdUseCase
import com.example.buved.presentation.FilterType
import com.example.buved.presentation.event.home.HomeUiEvent
import com.example.buved.presentation.event.home.NavigationEvent
import com.example.buved.presentation.state.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductIdUseCase: GetAllProductIdUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val insertProductIdUseCase: InsertProductIdUseCase,
    private val deleteProductIdUseCase: DeleteProductIdUseCase
): ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())

    private val _list = MutableStateFlow<List<Product>>(emptyList())
    private val _filteredList = MutableStateFlow<List<Product>>(emptyList())
    private val _productIdList = MutableStateFlow<List<ProductId>>(emptyList())

    val homeUiState: StateFlow<HomeUiState> = combine(_homeUiState, _filteredList, _productIdList){ uiState, filteredList, productIdList ->
        val favouriteIds = productIdList.map { it.productId }
        val updatedList = filteredList.map { if(it.id.toString() in favouriteIds) it.copy(isFavourite = true) else it }
        uiState.copy(
            favouriteCount = favouriteIds.size.toString(),
            list = updatedList
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        initialValue = HomeUiState()
    )

    private val _navEvent = MutableSharedFlow<NavigationEvent>()
    val navEvent = _navEvent.asSharedFlow()

    init {
        getProducts()
    }

    private fun getProducts(){
        viewModelScope.launch {
            _productIdList.value = getAllProductIdUseCase().first()

            getProductsUseCase().collect{it ->
                _homeUiState.value = _homeUiState.value.copy(list = it, isLoading = false)
                _list.value = it
                _filteredList.value = it
                Log.e("ProductUseCase", _homeUiState.value.list.toString())
            }
        }
    }

    fun onHomeEvent(event: HomeUiEvent){
        when(event){
            HomeUiEvent.onFilterClick -> {_homeUiState.value = _homeUiState.value.copy(moreOptionsExpanded = !_homeUiState.value.moreOptionsExpanded)}

            is HomeUiEvent.onFilter -> {
                _homeUiState.update { state ->
                    state.copy(
                        filterString = event.filterType.filterString,
                        filterType = event.filterType
                    )
                }
                if(event.filterType == FilterType.All){
                    _filteredList.value = _list.value
                }
                else{
                    _filteredList.value = _list.value.filter { it.category == event.filterType.filterType }
                }
                refreshProductList()
            }

            is HomeUiEvent.onProductFavourite -> {
                addFavourites(event.productId)
                refreshProductList()
                _filteredList.value = _filteredList.value.map {
                    if(it.id.toString() == event.productId) it.copy(isFavourite = !it.isFavourite) else it
                }
            }

            is HomeUiEvent.onProductRemoveFavourite -> {
                removeFavourites(event.productId)
                refreshProductList()
                _filteredList.value = _filteredList.value.map {
                    if(it.id.toString() == event.productId) it.copy(isFavourite = !it.isFavourite) else it
                }
            }
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

    private fun addFavourites(productID: String){
        viewModelScope.launch {
            val productIdNew = ProductId(
                productId = productID
            )
            insertProductIdUseCase(productIdNew)
        }
    }

    private fun removeFavourites(productIdFromProduct: String){
        val productId: ProductId = _productIdList.value.first {it->
            it.productId == productIdFromProduct
        }
        viewModelScope.launch {
            deleteProductIdUseCase(productId)
        }
    }

    private fun refreshProductList(){
        viewModelScope.launch {
            _productIdList.value = getAllProductIdUseCase().first()
        }
    }
}