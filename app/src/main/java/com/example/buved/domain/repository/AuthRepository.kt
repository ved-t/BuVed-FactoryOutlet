package com.example.buved.domain.repository

interface AuthRepository {
    suspend fun registerUser(email: String, password: String, onResult: (Boolean, String?) -> Unit)

    suspend fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit)

    suspend fun logOut()

    suspend fun isUserLoggedIn(): Boolean
}