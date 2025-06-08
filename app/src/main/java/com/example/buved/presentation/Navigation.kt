package com.example.buved.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.buved.presentation.ui.home.HomePage
import com.example.buved.presentation.ui.onboarding.ForgotPasswordPage
import com.example.buved.presentation.ui.onboarding.LoginPage
import com.example.buved.presentation.ui.onboarding.SignupPage
import com.example.buved.presentation.ui.onboarding.StartPage
import com.example.buved.presentation.ui.product_detail.ProductDetailScreen

@Composable
fun Navigation(

){
    val context = LocalContext.current
    val sharedPrefs =context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val navController = rememberNavController()

    val isLoggedIn = sharedPrefs.getBoolean("isLoggedIn", false)

//    val startPage = if(isLoggedIn) Destination.HomePage.route else Destination.StartPage.route

    NavHost(navController, startDestination = Destination.HomePage.route) {
        composable(Destination.StartPage.route){
            StartPage(navController)
        }
        composable(Destination.LoginPage.route){
            LoginPage(navController)
        }
        composable(Destination.SignInPage.route){
            SignupPage(navController)
        }
        composable(Destination.ForgotPasswordPage.route){
            ForgotPasswordPage(navController)
        }

        composable(Destination.HomePage.route){
            HomePage(navController)
        }
        composable(
            Destination.ProductDetailPage.route,
            arguments = listOf(navArgument("productId"){
                type = NavType.StringType
            })
        ){
            ProductDetailScreen(navController)
        }
    }
}