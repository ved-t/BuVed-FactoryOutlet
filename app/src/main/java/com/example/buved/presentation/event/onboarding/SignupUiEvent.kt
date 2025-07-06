package com.example.buved.presentation.event.onboarding

sealed class SignupUiEvent{
    data class onNameChange(val name: String): SignupUiEvent()
    data class onEmailChange(val email: String): SignupUiEvent()
    data class onPasswordChange(val password :String ): SignupUiEvent()
    data class onConfirmPasswordChange(val confirmPassword :String ): SignupUiEvent()

    data object onShowPassword: SignupUiEvent()
    data object onShowConfirmPassword: SignupUiEvent()

    data object onSIgnUp: SignupUiEvent()
}
