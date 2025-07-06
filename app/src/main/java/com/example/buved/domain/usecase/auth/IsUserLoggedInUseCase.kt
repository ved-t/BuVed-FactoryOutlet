package com.example.buved.domain.usecase.auth

import com.example.buved.domain.repository.AuthRepository

class IsUserLoggedInUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Boolean = authRepository.isUserLoggedIn()
}