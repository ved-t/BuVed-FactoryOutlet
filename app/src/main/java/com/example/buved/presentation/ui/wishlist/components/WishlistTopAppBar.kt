package com.example.buved.presentation.ui.wishlist.components

import androidx.compose.material.icons.Icons
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
import com.example.buved.presentation.ui.components.CircularImageIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistTopAppBar(
    onPopBackStack: () -> Unit,
    onNavigate: () -> Unit
){
    CenterAlignedTopAppBar(
        title = { Text(text = "Wishlist") },
        navigationIcon = {
            CircularImageIcon(
                R.drawable.go_back,
                "Go Back Icon",
                size = 25.dp,
                onClick = onPopBackStack
            )
        },
        actions = {
            IconButton(onClick = onNavigate) {
                Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart Icon")
            }
        }
    )
}