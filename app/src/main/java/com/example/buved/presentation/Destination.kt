package com.example.buved.presentation

sealed class Destination(
    val route: String
){
    data object StartPage: Destination("start_page")
    data object LoginPage: Destination("login_page")
    data object SignInPage: Destination("signin_page")
    data object ForgotPasswordPage: Destination("forgot_password_page")

    data object HomePage: Destination("home_page")

    data class ProductDetailPage(val productId: String): Destination("product_page/$productId"){
        companion object{
            fun createRoute(productId: String) = "product_page/$productId"
        }
    }

    data object WishListPage: Destination("wishlist_page")

    data object CartPage: Destination("cart_page")
}
