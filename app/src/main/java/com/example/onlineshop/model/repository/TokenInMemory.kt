package com.example.onlineshop.model.repository

object TokenInMemory {

    var username: String? = null
        private set

    var token: String? = null
        private set

    fun refreshToken(username: String? , newToken: String? ) {
        TokenInMemory.username = username
        token = newToken
    }

}