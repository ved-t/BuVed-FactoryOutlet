package com.example.buved.presentation.state.onboarding

data class SignUpUiState (
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val isShowPassword: Boolean = false,
    val isShowConfirmPassword: Boolean = false
)