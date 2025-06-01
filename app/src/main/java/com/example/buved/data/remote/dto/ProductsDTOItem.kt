package com.example.buved.data.remote.dto

import com.example.buved.domain.model.Product

data class ProductsDTOItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)

fun ProductsDTOItem.toDomain() = Product(
    category = category,
    description = description,
    id = id,
    image = image,
    price = price,
    rating = rating,
    title = title
)