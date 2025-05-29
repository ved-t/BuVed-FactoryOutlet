package com.example.buved.presentation.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.buved.presentation.Destination

@Composable
fun StartPage(
    navController: NavController
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .padding(bottom = 48.dp),
            onClick = {
                navController.navigate(Destination.LoginPage.route)
            }
        ) {
            Text("GET STARTED",
                fontSize = 16.sp)
        }
    }
}