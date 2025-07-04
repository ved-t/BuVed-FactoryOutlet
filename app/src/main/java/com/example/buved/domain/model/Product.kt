package com.example.buved.domain.model

import com.example.buved.data.remote.dto.Rating

data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String,
    val isFavourite: Boolean = false
)


