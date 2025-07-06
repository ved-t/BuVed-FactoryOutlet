package com.example.buved.domain.usecase.auth

import com.example.buved.domain.repository.AuthRepository

class LoginUserUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, onResult: (Boolean, String?) -> Unit) = authRepository.loginUser(
        email = email, password = password,
        onResult = onResult
    )
}