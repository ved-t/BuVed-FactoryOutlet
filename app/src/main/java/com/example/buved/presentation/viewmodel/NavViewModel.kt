package com.example.buved.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.presentation.event.home.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavViewModel @Inject constructor(): ViewModel() {
    private val _navEvent = MutableSharedFlow<NavigationEvent>()
    val navEvent = _navEvent.asSharedFlow()

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
}