package com.example.buved.presentation

sealed class Destination(
    val route: String
){
    data object StartPage: Destination("start_page")
    data object LoginPage: Destination("login_page")
    data object SignInPage: Destination("signin_page")
    data object ForgotPasswordPage: Destination("forgot_password_page")

    data object HomePage: Destination("home_page")
    data object ProductDetailPage: Destination("product_page/{productId}"){
        fun createRoute(productId: String) = "product_page/$productId"
    }
}
