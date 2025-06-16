package com.example.buved.presentation.ui.product_detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.buved.domain.model.Product
import com.example.buved.presentation.Destination
import com.example.buved.presentation.event.home.NavigationEvent
import com.example.buved.presentation.event.home.ProductUiEvent
import com.example.buved.presentation.ui.components.LoadingIndicator
import com.example.buved.presentation.ui.components.MyTopAppBar
import com.example.buved.presentation.ui.product_detail.components.ImageCard
import com.example.buved.presentation.ui.product_detail.components.ProductDetailTopAppBar
import com.example.buved.presentation.viewmodel.product_detail.ProductViewModel

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
){
    LaunchedEffect(Unit) {
        productViewModel.navEvent.collect{event ->
            when(event){
                is NavigationEvent.Navigate -> {
                    navController.navigate(event.destination.route)
                }

                NavigationEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }

    val productId = productViewModel.productId
    val productUiState by productViewModel.productUiState.collectAsState()

    Scaffold(
        modifier = Modifier,
        topBar = { ProductDetailTopAppBar(
            onPopBackStack = { productViewModel.onNavEvent(NavigationEvent.PopBackStack) },
            onNavigate = {productViewModel.onNavEvent(NavigationEvent.Navigate(Destination.CartPage))}
        ) },

    ) {innerPadding ->
        if(!productUiState.isLoading){
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                if(productUiState.product != null){
                    ImageCard(productUiState.product!!)
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = productUiState.product?.title ?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "$" + productUiState.product?.price.toString(),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = productUiState.product?.description ?: "",
                    fontSize = 16.sp,
                )
                Spacer(Modifier.height(16.dp))
                Spacer(Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        productViewModel.onEvent(ProductUiEvent.onAddToCart(productUiState.product!!.id))
                    }
                ) {
                    Text(
                        text = "Add to Cart",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        else if(productUiState.errorString != null){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = productUiState.errorString ?: "Could not fetch error string",
                    fontSize = 24.sp,
                    color = Color.Red
                )
            }
        }
        else if(productUiState.isLoading){
            Log.d("uiState", "Displaying Loading Indicator")
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LoadingIndicator()
            }
        }
    }
}