package com.example.buved.presentation.ui.onboarding

import android.widget.Space
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
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.buved.R
import com.example.buved.presentation.Destination
import com.example.buved.presentation.event.onboarding.LoginUiEvent
import com.example.buved.presentation.ui.onboarding.components.CircularImageIcon
import com.example.buved.presentation.viewmodel.onboarding.LoginViewModel
import java.security.Key

@Composable
fun LoginPage(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(top = 150.dp)
        ) {
            Text(
                text = "Log In",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = Color.White
            )

            Text(
                text = "Please sign in to your existing account.",
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = Color.White
            )
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
                text = "EMAIL",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.email,
                onValueChange = {viewModel.onEvent(LoginUiEvent.onEmailChange(it))},
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
                onValueChange = {viewModel.onEvent(LoginUiEvent.onPasswordChange(it))},
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
                        viewModel.onEvent(LoginUiEvent.onShowPassword)
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Checkbox(
                    checked = uiState.isRememberMe,
                    onCheckedChange = {
                        viewModel.onEvent(LoginUiEvent.onRememberMe)
                    }
                )
                Text(
                    text = "Remember me",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = {
                        navController.navigate(Destination.ForgotPasswordPage.route)
                    }
                ) {
                    Text(
                        text = "Forgot Password",
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick ={
                    navController.navigate(Destination.HomePage.route)
                }
            ) {
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Don't have an account? ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                TextButton(
                    onClick = {
                        navController.navigate(Destination.SignInPage.route)
                    }
                ) {
                    Text(
                        text = "SIGN UP",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Or",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                CircularImageIcon(
                    R.drawable.face_book_logo,
                    "Face Book Logo"
                )

                CircularImageIcon(
                    R.drawable.twitter_logo,
                    "Face Book Logo"
                )

                CircularImageIcon(
                    R.drawable.apple_logo,
                    "Face Book Logo"
                )
            }

        }
    }
}

@Composable
@Preview
fun LoginPagePreview(){
    LoginPage(navController = rememberNavController())
}