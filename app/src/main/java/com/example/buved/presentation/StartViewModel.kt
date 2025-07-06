package com.example.buved.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.usecase.auth.IsUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
): ViewModel() {
    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    init {
        isUserLogedIn()
    }

    private fun isUserLogedIn(){
        viewModelScope.launch {
            _isUserLoggedIn.value = isUserLoggedInUseCase()
        }
    }
}
