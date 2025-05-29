package com.example.buved.presentation.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import com.example.buved.presentation.event.onboarding.LoginUiEvent
import com.example.buved.presentation.event.onboarding.SignupUiEvent
import com.example.buved.presentation.state.onboarding.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel()  {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState: StateFlow<SignUpUiState> = _signUpUiState

    fun onEvent(event: SignupUiEvent){
        when(event){
            is SignupUiEvent.onConfirmPasswordChange -> {_signUpUiState.value = _signUpUiState.value.copy(confirmPassword = event.confirmPassword)}
            is SignupUiEvent.onEmailChange -> {_signUpUiState.value = _signUpUiState.value.copy(email = event.email)}
            is SignupUiEvent.onNameChange -> {_signUpUiState.value = _signUpUiState.value.copy(name = event.name)}
            is SignupUiEvent.onPasswordChange -> {_signUpUiState.value = _signUpUiState.value.copy(password = event.password)}

            SignupUiEvent.onShowConfirmPassword -> {_signUpUiState.value = _signUpUiState.value.copy(isShowConfirmPassword = !_signUpUiState.value.isShowConfirmPassword)}
            SignupUiEvent.onShowPassword -> {_signUpUiState.value = _signUpUiState.value.copy(isShowPassword = !_signUpUiState.value.isShowPassword)}
        }
    }
}