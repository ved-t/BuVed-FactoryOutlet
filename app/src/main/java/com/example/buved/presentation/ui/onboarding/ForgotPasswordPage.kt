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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.buved.R
import com.example.buved.presentation.Destination
import com.example.buved.presentation.event.onboarding.ForgotPasswordUiEvent
import com.example.buved.presentation.event.onboarding.SignupUiEvent
import com.example.buved.presentation.ui.onboarding.components.CircularImageIcon
import com.example.buved.presentation.viewmodel.onboarding.ForgotPasswordViewModel

@Composable
fun ForgotPasswordPage(navController: NavHostController, viewModel: ForgotPasswordViewModel = hiltViewModel()) {
    val uiState by viewModel.forgotPasswordUiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
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
                    size = 45.dp
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
                text = "EMAIL",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.email,
                onValueChange = {viewModel.onEvent(ForgotPasswordUiEvent.onEmailChange(it))},
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

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate(Destination.LoginPage.route)
                }
            ) {
                Text(
                    text = "SEND CODE",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}