package com.example.buved.presentation.viewmodel.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.model.Product
import com.example.buved.domain.usecase.GetProductsUseCase
import com.example.buved.presentation.event.home.HomeUiEvent
import com.example.buved.presentation.event.home.NavigationEvent
import com.example.buved.presentation.state.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
): ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())

    private val _list = MutableStateFlow<List<Product>>(emptyList())
    private val _filteredList = MutableStateFlow<List<Product>>(emptyList())

    val homeUiState: StateFlow<HomeUiState> = combine(_homeUiState, _filteredList){ uiState, filteredList ->
        uiState.copy(
            list = filteredList
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
                _filteredList.value = _list.value.filter { it.category == event.filterType.filterType }
            }


            is HomeUiEvent.onProductFavourite -> {
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
                    _navEvent.emit(NavigationEvent.Navigate(event.id))
                    Log.d("nav", event.id)
                }
            }
            NavigationEvent.PopBackStack -> {}
        }
    }
}