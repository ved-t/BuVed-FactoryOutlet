package com.example.buved.presentation.ui.cart.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.buved.R
import com.example.buved.presentation.ui.components.BadgedBoxIcon
import com.example.buved.presentation.ui.components.CircularImageIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopAppBar(
    navController: NavController,

){
    CenterAlignedTopAppBar(
        title = { Text(text = "Cart") },
        navigationIcon = {
            CircularImageIcon(
                R.drawable.go_back,
                "Go Back Icon",
                size = 25.dp,
                onClick = {
                    navController.popBackStack()
                }
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Delete, contentDescription = "Delete Icon")
            }
        }
    )
}