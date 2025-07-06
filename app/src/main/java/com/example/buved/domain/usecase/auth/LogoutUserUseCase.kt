package com.example.buved.domain.usecase.auth

import com.example.buved.domain.repository.AuthRepository

class LogoutUserUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.logOut()
}