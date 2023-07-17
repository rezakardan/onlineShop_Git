package com.example.onlineshop.model.data

import com.example.onlineshop.model.data.Product

data class UserCartInfo(
    val success: Boolean,
    val productList: List<Product>,
    val message: String,
    val totalPrice: Int
)