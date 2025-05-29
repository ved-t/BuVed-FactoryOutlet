package com.example.buved.presentation.event.onboarding

sealed class LoginUiEvent {
    data class onEmailChange(val email: String): LoginUiEvent()
    data class onPasswordChange(val password :String ): LoginUiEvent()
    data object onRememberMe: LoginUiEvent()
    data object onShowPassword: LoginUiEvent()

    data class navigate(val route: String): LoginUiEvent()
    data object onForgotPassword: LoginUiEvent()
    data object onLogin: LoginUiEvent()
    data object onSignUp: LoginUiEvent()

}