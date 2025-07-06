package com.example.buved.presentation.viewmodel.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.usecase.auth.LoginUserUseCase
import com.example.buved.presentation.Destination
import com.example.buved.presentation.event.onboarding.LoginUiEvent
import com.example.buved.presentation.state.onboarding.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
): ViewModel(){
    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState())
    private val _isLoggedIn = MutableStateFlow<Boolean>(false)
    private val _authError = MutableStateFlow<String?>(null)

    val uiState: StateFlow<LoginUiState> = _loginUiState

    fun onEvent(event: LoginUiEvent){
        when(event){
            is LoginUiEvent.onEmailChange -> {_loginUiState.value = _loginUiState.value.copy(email = event.email)}
            is LoginUiEvent.onPasswordChange -> {_loginUiState.value = _loginUiState.value.copy(password = event.password)}
            LoginUiEvent.onRememberMe -> {_loginUiState.value = _loginUiState.value.copy(isRememberMe = !_loginUiState.value.isRememberMe)}
            LoginUiEvent.onShowPassword -> {_loginUiState.value = _loginUiState.value.copy(isShowPassword = !_loginUiState.value.isShowPassword)}

            LoginUiEvent.onLogin -> {
                loginUser()
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

    private fun loginUser(){
        val email = _loginUiState.value.email
        val password = _loginUiState.value.password
        if(email.isBlank() || password.isBlank()){
            return
        }
        viewModelScope.launch {
            loginUserUseCase(email, password){success, message ->
                _isLoggedIn.value = success
                _authError.value = if(success) null else message

                _loginUiState.value = _loginUiState.value.copy(isLoggedIn = _isLoggedIn.value, authErrorString = _authError.value)
            }
        }
    }
}