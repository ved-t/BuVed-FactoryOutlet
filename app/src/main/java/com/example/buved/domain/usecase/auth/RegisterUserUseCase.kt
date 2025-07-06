package com.example.buved.domain.usecase.auth

import com.example.buved.domain.repository.AuthRepository

class RegisterUserUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, onResult: (Boolean, String?) -> Unit) = authRepository.registerUser(
        email = email, password = password,
        onResult = onResult
    ) 
}