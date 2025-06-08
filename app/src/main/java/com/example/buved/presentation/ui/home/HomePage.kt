package com.example.buved.presentation.ui.home

import android.util.Log
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
import com.example.buved.presentation.viewmodel.home.HomeViewModel

@Composable
fun HomePage(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        homeViewModel.navEvent.collect{event ->
            when(event){
                is NavigationEvent.Navigate -> {
                    navController.navigate(Destination.ProductDetailPage.createRoute(event.id))
                    Log.d("nav", Destination.ProductDetailPage.createRoute(event.id))
                }

                NavigationEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar =  {
            MyTopAppBar()
        },
        bottomBar = {
            MyBottomAppBar()
        }
    ) { innerPadding ->

        val uiState by homeViewModel.homeUiState.collectAsState()

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
                            onNavigate = {
                                homeViewModel.onNavEvent(
                                    NavigationEvent.Navigate(product.id.toString())
                                )
                            }
                        )
                    }
                }
            }
            else{
                LoadingIndicator()
            }
        }

    }
}

