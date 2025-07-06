package com.example.buved.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.buved.presentation.Destination
import com.example.buved.presentation.event.home.HomeUiEvent
import com.example.buved.presentation.event.home.NavigationEvent
import com.example.buved.presentation.ui.components.LoadingIndicator
import com.example.buved.presentation.ui.components.MyBottomAppBar
import com.example.buved.presentation.ui.components.MyTopAppBar
import com.example.buved.presentation.ui.home.components.MoreOptions
import com.example.buved.presentation.ui.home.components.ProductItem
import com.example.buved.presentation.viewmodel.NavViewModel
import com.example.buved.presentation.viewmodel.cart.CartViewModel
import com.example.buved.presentation.viewmodel.home.HomeViewModel

@Composable
fun HomePage(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel(), cartViewModel: CartViewModel = hiltViewModel()) {

    val navViewModel: NavViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        navViewModel.navEvent.collect{event ->
            when(event){
                is NavigationEvent.Navigate -> {
                    navController.navigate(event.destination.route)
                }

                NavigationEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }

    val uiState by homeViewModel.homeUiState.collectAsState()
    val cartUiState by cartViewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier,
        topBar =  {
            MyTopAppBar(
                wishlistBadgeCount = uiState.favouriteCount,
                onNavigate = {
                    navViewModel.onNavEvent(NavigationEvent.Navigate(Destination.WishListPage))
                }
            )
        },
        bottomBar = {
            MyBottomAppBar(
                onNavigate = {
                    navViewModel.onNavEvent(NavigationEvent.Navigate(Destination.CartPage))
                },
                cartItemCount = cartUiState.totalUniqueItems.toString()
            )
        }
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ){
                Text(
                    text = uiState.filterString
                )

                Spacer(modifier = Modifier.weight(1f))

                MoreOptions()
            }


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
                            onFavourite = {
                                homeViewModel.onHomeEvent(
                                    HomeUiEvent.onProductFavourite(
                                        product.id.toString()
                                    )
                                )
                            },
                            onRemoveFavourite = {
                                homeViewModel.onHomeEvent(
                                    HomeUiEvent.onProductRemoveFavourite(
                                        product.id.toString()
                                    )
                                )
                            },
                            onNavigate = {
                                navViewModel.onNavEvent(
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

