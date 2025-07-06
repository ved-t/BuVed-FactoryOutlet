package com.example.buved.presentation.viewmodel.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buved.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.buved.domain.usecase.auth.RegisterUserUseCase
import com.example.buved.presentation.event.onboarding.LoginUiEvent
import com.example.buved.presentation.event.onboarding.SignupUiEvent
import com.example.buved.presentation.state.onboarding.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
): ViewModel()  {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    private val _isLoggedIn = MutableStateFlow<Boolean>(false)
    private val _authError = MutableStateFlow<String?>(null)


    val signUpUiState: StateFlow<SignUpUiState> = _signUpUiState

    fun onEvent(event: SignupUiEvent){
        when(event){
            is SignupUiEvent.onConfirmPasswordChange -> {_signUpUiState.value = _signUpUiState.value.copy(confirmPassword = event.confirmPassword)}
            is SignupUiEvent.onEmailChange -> {_signUpUiState.value = _signUpUiState.value.copy(email = event.email)}
            is SignupUiEvent.onNameChange -> {_signUpUiState.value = _signUpUiState.value.copy(name = event.name)}
            is SignupUiEvent.onPasswordChange -> {_signUpUiState.value = _signUpUiState.value.copy(password = event.password)}

            SignupUiEvent.onShowConfirmPassword -> {_signUpUiState.value = _signUpUiState.value.copy(isShowConfirmPassword = !_signUpUiState.value.isShowConfirmPassword)}
            SignupUiEvent.onShowPassword -> {_signUpUiState.value = _signUpUiState.value.copy(isShowPassword = !_signUpUiState.value.isShowPassword)}
            SignupUiEvent.onSIgnUp -> signUp()
        }
    }

    private fun signUp(){
        val email: String = _signUpUiState.value.email
        val password: String = _signUpUiState.value.password
        val confirmPassword: String = _signUpUiState.value.confirmPassword

        if(email.isBlank() || password.isBlank()){
            return
        }
        if(password != confirmPassword){
            Log.d("Login", "$password, $confirmPassword, Not true Signup unsuccessful")
        }
        else{
            viewModelScope.launch {
                registerUserUseCase(email, password){success, message ->
                    _isLoggedIn.value = success
                    _authError.value = if(success) null else message

                    _signUpUiState.value = _signUpUiState.value.copy(isLoggedIn = _isLoggedIn.value, authErrorString = _authError.value)
                }
            }
        }
    }
}