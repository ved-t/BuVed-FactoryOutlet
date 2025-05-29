package com.example.buved.presentation.event.onboarding

sealed class ForgotPasswordUiEvent {
    data class onEmailChange(val email: String): ForgotPasswordUiEvent()
}