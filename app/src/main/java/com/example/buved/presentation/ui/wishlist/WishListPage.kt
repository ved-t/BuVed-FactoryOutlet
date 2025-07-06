package com.example.buved.presentation.ui.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.buved.presentation.Destination
import com.example.buved.presentation.event.home.HomeUiEvent
import com.example.buved.presentation.event.home.NavigationEvent
import com.example.buved.presentation.event.home.WishlistUiEvent
import com.example.buved.presentation.ui.components.LoadingIndicator
import com.example.buved.presentation.ui.components.MyBottomAppBar
import com.example.buved.presentation.ui.components.MyTopAppBar
import com.example.buved.presentation.ui.home.components.ProductItem
import com.example.buved.presentation.ui.wishlist.components.WishlistTopAppBar
import com.example.buved.presentation.viewmodel.cart.CartViewModel
import com.example.buved.presentation.viewmodel.wishlist.WishlistViewModel

@Composable
fun WishListPage(
    navController: NavController,
    viewModel: WishlistViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
){

    val cartUiState by cartViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navEvent.collect{event ->
            when(event){
                is NavigationEvent.Navigate -> {
                    navController.navigate(event.destination.route)
                }

                NavigationEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar =  {
            WishlistTopAppBar(
                onPopBackStack = {viewModel.onNavEvent(NavigationEvent.PopBackStack)},
                onNavigate = {viewModel.onNavEvent(NavigationEvent.Navigate(Destination.CartPage))}
            )
        },
        bottomBar = {
            MyBottomAppBar(
                onNavigate = {
                    viewModel.onNavEvent(NavigationEvent.Navigate(Destination.CartPage))
                },
                cartItemCount = cartUiState.totalUniqueItems.toString()
            )
        }
    ){innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){

            val uiState by viewModel.uiState.collectAsState()

            if(!uiState.isLoading){
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(250.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    items(uiState.list){ product ->
                        ProductItem(
                            product,
                            onFavourite = {},
                            onRemoveFavourite = {viewModel.onEvent(WishlistUiEvent.onProductRemoveFavourite(product.id));},
                            onNavigate = {
                                viewModel.onNavEvent(
                                    NavigationEvent.Navigate(Destination.ProductDetailPage(product.id.toString()))
                                )
                            }
                        )
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