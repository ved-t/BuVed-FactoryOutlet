package com.example.buved.presentation.state.onboarding

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val isRememberMe: Boolean = false,
    val isShowPassword: Boolean = false,
    val navigationRoute: String? = null,

    val isLoggedIn: Boolean = false,
    val authErrorString: String? = null
)