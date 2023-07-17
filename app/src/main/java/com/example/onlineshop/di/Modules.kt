package com.example.onlineshop.di

import android.content.Context
import androidx.room.Room
import com.example.onlineshop.model.db.AppDatabase
import com.example.onlineshop.model.net.createApiService
import com.example.onlineshop.model.repository.cart.CartRepository
import com.example.onlineshop.model.repository.cart.CartRepositoryImpl
import com.example.onlineshop.model.repository.comment.CommentRepository
import com.example.onlineshop.model.repository.comment.CommentRepositoryImpl
import com.example.onlineshop.model.repository.product.ProductRepository
import com.example.onlineshop.model.repository.product.ProductRepositoryImpl
import com.example.onlineshop.model.repository.user.UserRepository
import com.example.onlineshop.model.repository.user.UserRepositoryImpl
import com.example.onlineshop.cart.CategoryViewModel
import ir.dunijet.dunibazaar.ui.features.category.CartViewModel
import com.example.onlineshop.main.MainViewModel
import com.example.onlineshop.product.ProductViewModel
import com.example.onlineshop.profile.ProfileViewModel
import com.example.onlineshop.signIn.SignInViewModel
import com.example.onlineshop.signUp.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single { androidContext().getSharedPreferences("data", Context.MODE_PRIVATE) }
    single { createApiService() }

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_dataBase.db").build() }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(), get<AppDatabase>().productDao()) }
    single<CommentRepository> { CommentRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get() , get()) }

 viewModel { CategoryViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { ProductViewModel(get(), get(), get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { (isNetConnected: Boolean) -> MainViewModel(get(), get() , isNetConnected) }
    viewModel { CartViewModel(get() , get()) }

}