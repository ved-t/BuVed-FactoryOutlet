package com.example.buved.presentation.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import com.example.buved.presentation.Destination
import com.example.buved.presentation.event.onboarding.LoginUiEvent
import com.example.buved.presentation.state.onboarding.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel(){
    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _loginUiState

    fun onEvent(event: LoginUiEvent){
        when(event){
            is LoginUiEvent.onEmailChange -> {_loginUiState.value = _loginUiState.value.copy(email = event.email)}
            is LoginUiEvent.onPasswordChange -> {_loginUiState.value = _loginUiState.value.copy(password = event.password)}
            LoginUiEvent.onRememberMe -> {_loginUiState.value = _loginUiState.value.copy(isRememberMe = !_loginUiState.value.isRememberMe)}
            LoginUiEvent.onShowPassword -> {_loginUiState.value = _loginUiState.value.copy(isShowPassword = !_loginUiState.value.isShowPassword)}

            LoginUiEvent.onLogin -> {
//                Do Some verification
            }

            LoginUiEvent.onForgotPassword -> {
                _loginUiState.value = _loginUiState.value.copy(navigationRoute = Destination.ForgotPasswordPage.route)
            }
            LoginUiEvent.onSignUp -> {
                _loginUiState.value = _loginUiState.value.copy(navigationRoute = Destination.SignInPage.route)
            }

            is LoginUiEvent.navigate -> TODO()
        }
    }
}