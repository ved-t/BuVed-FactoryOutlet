package com.example.buved.presentation

enum class FilterType(
    val filterType: String,
    val filterString: String
) {
    All("", "Recommendation"),
    MensClothing("men's clothing", "Men's Clothing"),
    Jewelery("jewelery", "Jewelery"),
    Electronics("electronics", "Electronics"),
    WomensClothing("women's clothing", "Women's Clothing")
}