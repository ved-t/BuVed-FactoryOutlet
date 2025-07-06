package com.example.buved.presentation.ui.cart

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.buved.presentation.event.home.CartUiEvent
import com.example.buved.presentation.ui.cart.components.CartProductItem
import com.example.buved.presentation.ui.cart.components.CartTopAppBar
import com.example.buved.presentation.ui.cart.components.CircularBadgeText
import com.example.buved.presentation.ui.components.LoadingIndicator
import com.example.buved.presentation.viewmodel.cart.CartViewModel

@Composable
fun CartPage(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            CartTopAppBar(navController);
        }
    ) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if(!uiState.isLoading){
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(250.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    itemsIndexed(
                        uiState.productList,
                    ){ index, product ->
                        CartProductItem(
                            product = product,
                            price = uiState.totalItemCost[index].toString(),
                            onFavourite = {},
                            onRemoveFavourite = {},
                            onNavigate = {},
                            itemQuantity = uiState.cartList[index].productQuantity.toString(),

                            onIncrement = {viewModel.onEvent(CartUiEvent.onItemIncrement(uiState.cartList[index]))},
                            onDecrement = {viewModel.onEvent(CartUiEvent.onItemDecrement(uiState.cartList[index]))},
                            isTouchDisabled = uiState.isBackgroundLoading
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Column (){
                    Text(
                        text = "Total Amount",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$" + uiState.totalAmount,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {}
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier

                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Proceed To checkout",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.width(16.dp))

                        CircularBadgeText(uiState.totalItems.toString())
                    }
                }
            }
            else{
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
}