package com.example.buved.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.buved.presentation.ui.cart.CartPage
import com.example.buved.presentation.ui.home.HomePage
import com.example.buved.presentation.ui.onboarding.ForgotPasswordPage
import com.example.buved.presentation.ui.onboarding.LoginPage
import com.example.buved.presentation.ui.onboarding.SignupPage
import com.example.buved.presentation.ui.onboarding.StartPage
import com.example.buved.presentation.ui.product_detail.ProductDetailScreen
import com.example.buved.presentation.ui.wishlist.WishListPage

@Composable
fun Navigation(startViewModel: StartViewModel = hiltViewModel()){
    val context = LocalContext.current
    val sharedPrefs =context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val navController = rememberNavController()

    val isLoggedIn by startViewModel.isUserLoggedIn.collectAsState()

    val startPage = if(isLoggedIn) Destination.HomePage.route else Destination.StartPage.route

    NavHost(navController, startDestination = startPage) {
        composable(Destination.StartPage.route){
            StartPage(navController)
        }
        composable(Destination.LoginPage.route){
            LoginPage(navController)
        }
        composable(Destination.SignInPage.route){
            SignupPage(navController)
        }

//        Forgot Password Page
        composable(Destination.ForgotPasswordPage.route){
            ForgotPasswordPage(navController)
        }

//        Home
        composable(Destination.HomePage.route){
            HomePage(navController)
        }

//        Product Detail Page
        composable(
            "product_page/{productId}",
            arguments = listOf(navArgument("productId"){
                type = NavType.StringType
            })
        ){
            ProductDetailScreen(navController)
        }

//        Wishlist Page
        composable(
            Destination.WishListPage.route,
        ){
            WishListPage(navController)
        }

//        Cart Page
        composable(
            Destination.CartPage.route,
        ){
            CartPage(navController)
        }
    }
}