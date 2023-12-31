package com.example.onlineshop.model.repository.product

import com.example.onlineshop.model.data.Ads
import com.example.onlineshop.model.data.Product
import com.example.onlineshop.model.db.ProductDao
import com.example.onlineshop.model.net.ApiService
import com.example.onlineshop.model.repository.product.ProductRepository

class ProductRepositoryImpl(
    private val apiService: ApiService,
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun getAllProducts(isInternetConnected: Boolean): List<Product> {

        if (isInternetConnected) {

            // get data from net
            val dataFromServer = apiService.getAllProducts()
            if (dataFromServer.success) {
                productDao.insertOrUpdate(dataFromServer.products)
                return dataFromServer.products
            }

        } else {

            // get data from local
            return productDao.getAll()

        }

        return listOf()
    }

    override suspend fun getAllAds(isInternetConnected: Boolean): List<Ads> {

        if (isInternetConnected) {

            // get ads

            val dataFromServer = apiService.getAllAds()
            if (dataFromServer.success) {
                return dataFromServer.ads
            }

        }


        return listOf()
    }

    override suspend fun getAllProductsByCategory(category: String): List<Product> {
        return productDao.getAllByCategory(category)
    }

    override suspend fun getProductById(productId: String): Product {
        return productDao.getProductById(productId)
    }


}