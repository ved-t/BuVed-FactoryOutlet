package com.example.buved.presentation.ui.product_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.buved.presentation.viewmodel.product_detail.ProductViewModel

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
){
    val productId = productViewModel.productId

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Details Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = productId,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )

    }
}