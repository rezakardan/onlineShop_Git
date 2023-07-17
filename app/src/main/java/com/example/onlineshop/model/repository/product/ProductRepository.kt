package com.example.onlineshop.model.repository.product

import com.example.onlineshop.model.data.Ads
import com.example.onlineshop.model.data.Product

interface ProductRepository {

    suspend fun getAllProducts(isInternetConnected :Boolean): List<Product>
    suspend fun getAllAds(isInternetConnected :Boolean): List<Ads>
    suspend fun getAllProductsByCategory(category :String) :List<Product>
    suspend fun getProductById(productId :String) : Product

}