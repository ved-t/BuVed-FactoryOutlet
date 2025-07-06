package com.example.buved.data.repository

import android.util.Log
import com.example.buved.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    override suspend fun registerUser(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) {
       firebaseAuth.createUserWithEmailAndPassword(email, password)
           .addOnCompleteListener {
               if(it.isSuccessful) {
                   Log.w("Login", "Sign up Successfull")
                   onResult(true, null)
               }
               else {
                   Log.w("Login", "Sign upSuccessfull: ${it.exception}")
                   onResult(false, it.exception?.message)
               }
           }

    }

    override suspend fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    onResult(true, null)
                    Log.w("Login", "Sign up Successfull")
                }
                else {
                    onResult(false, it.exception?.message)
                    Log.w("Login", "Sign up Successfull: ${it.exception}")
                }
            }
    }

    override suspend fun logOut() {
        firebaseAuth.signOut()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

}