package com.example.buved.presentation.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.buved.R
import com.example.buved.presentation.Destination
import com.example.buved.presentation.event.home.NavigationEvent
import com.example.buved.presentation.event.onboarding.SignupUiEvent
import com.example.buved.presentation.ui.components.CircularImageIcon
import com.example.buved.presentation.viewmodel.NavViewModel
import com.example.buved.presentation.viewmodel.onboarding.SignUpViewModel

@Composable
fun SignupPage(navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel() ) {

    val uiState by viewModel.signUpUiState.collectAsState()

    val navViewModel: NavViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        navViewModel.navEvent.collect{event ->
            when(event){
                is NavigationEvent.Navigate -> {
                    navController.navigate(event.destination.route)
                }

                NavigationEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box{
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                CircularImageIcon(
                    R.drawable.go_back,
                    "Go Back Icon",
                    size = 45.dp,
                    onClick = {
                        navController.popBackStack()
                    }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(top = 150.dp)
            ) {
                Text(
                    text = "Sign Up",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.White
                )

                Text(
                    text = "Please sign in to get started.",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(
                    RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                )
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Name",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.name,
                onValueChange = {viewModel.onEvent(SignupUiEvent.onNameChange(it))},
                placeholder = {
                    Text(
                        text = "John Doe",
                        fontSize = 12.sp
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "EMAIL",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.email,
                onValueChange = {viewModel.onEvent(SignupUiEvent.onEmailChange(it))},
                placeholder = {
                    Text(
                        text = "example@gmail.com",
                        fontSize = 12.sp
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "PASSWORD",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.password,
                onValueChange = {viewModel.onEvent(SignupUiEvent.onPasswordChange(it))},
                placeholder = {
                    Text(
                        text = "password",
                        fontSize = 12.sp
                    )
                },
                singleLine = true,
                visualTransformation = if(uiState.isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(SignupUiEvent.onShowPassword)
                    }) {
                        Icon(
                            if (uiState.isShowPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (uiState.isShowPassword) "Hide Password" else "Show Password"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "CONFIRM PASSWORD",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.confirmPassword,
                onValueChange = {viewModel.onEvent(SignupUiEvent.onConfirmPasswordChange(it))},
                placeholder = {
                    Text(
                        text = "confirm password",
                        fontSize = 12.sp
                    )
                },
                singleLine = true,
                visualTransformation = if(uiState.isShowConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(SignupUiEvent.onShowConfirmPassword)
                    }) {
                        Icon(
                            if (uiState.isShowConfirmPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (uiState.isShowConfirmPassword) "Hide Password" else "Show Password"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel.onEvent(SignupUiEvent.onSIgnUp)
                    if(uiState.isLoggedIn){
                        navViewModel.onNavEvent(NavigationEvent.Navigate(Destination.HomePage))
                    }
                }
            ) {
                Text(
                    text = "SIGN UP",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}