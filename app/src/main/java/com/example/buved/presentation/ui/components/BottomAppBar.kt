package com.example.buved.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MyBottomAppBar(
    onNavigate: () ->Unit,
){
    BottomAppBar() {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home Icon") },
            selected = false,
            label = {
                Text("Home")
            },
            onClick = {  }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart Icon") },
            selected = false,
            label = {
                Text("Cart")
            },
            onClick = onNavigate,
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings Icon") },
            selected = false,
            label = {
                Text("Settings")
            },
            onClick = { },
        )
    }
}