package com.example.buved.presentation.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import com.example.buved.presentation.event.onboarding.ForgotPasswordUiEvent
import com.example.buved.presentation.state.onboarding.ForgotPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(): ViewModel(){
    private val _forgotPasswordUiState = MutableStateFlow(ForgotPasswordUiState())
    val forgotPasswordUiState: StateFlow<ForgotPasswordUiState> = _forgotPasswordUiState

    fun onEvent(event: ForgotPasswordUiEvent){
        when(event){
            is ForgotPasswordUiEvent.onEmailChange -> {_forgotPasswordUiState.value = _forgotPasswordUiState.value.copy(email = event.email)}
        }
    }
}