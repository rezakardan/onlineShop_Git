package com.example.onlineshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlineshop.cart.CategoryScreen
import com.example.onlineshop.category.CartScreen
import com.example.onlineshop.di.myModules
import com.example.onlineshop.main.MainScreen
import com.example.onlineshop.model.repository.TokenInMemory
import com.example.onlineshop.model.repository.user.UserRepository
import com.example.onlineshop.product.ProductScreen
import com.example.onlineshop.profile.ProfileScreen
import com.example.onlineshop.signIn.SignInScreen
import com.example.onlineshop.signUp.SignUpScreen
import com.example.onlineshop.ui.theme.BackgroundMain
import com.example.onlineshop.ui.theme.OnlineShopTheme
import com.example.onlineshop.util.KEY_CATEGORY_ARG
import com.example.onlineshop.util.KEY_PRODUCT_ARG
import com.example.onlineshop.util.MyScreens
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)
            }) {
                OnlineShopTheme(){
                    Surface(color = BackgroundMain, modifier = Modifier.fillMaxSize()) {

                        val userRepository : UserRepository = get()
                        userRepository.loadToken()

                        OnlineShop()

                    }
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnlineShopTheme() {
        Surface(
            color = BackgroundMain,
            modifier = Modifier.fillMaxSize()
        ) {
            OnlineShop()
        }
    }
}

@Composable
fun OnlineShop() {
    val navController = rememberNavController()
    KoinNavHost(navController = navController, startDestination = MyScreens.MainScreen.route) {

        composable(MyScreens.MainScreen.route) {

            if(TokenInMemory.token != null) {
                MainScreen()
            } else {
                IntroScreen()
            }

        }

        composable(
            route = MyScreens.ProductScreen.route + "/" + "{$KEY_PRODUCT_ARG}",
            arguments = listOf(navArgument(KEY_PRODUCT_ARG) {
                type = NavType.StringType
            })
        ) {

            ProductScreen(it.arguments!!.getString(KEY_PRODUCT_ARG, "null"))

        }

        composable(
            route = MyScreens.CategoryScreen.route + "/" + "{$KEY_CATEGORY_ARG}",
            arguments = listOf(navArgument(KEY_CATEGORY_ARG) {
                type = NavType.StringType
            })
        ) {
            CategoryScreen(it.arguments!!.getString(KEY_CATEGORY_ARG, "null"))
        }

        composable(MyScreens.ProfileScreen.route) {
            ProfileScreen()
        }

        composable(MyScreens.CartScreen.route) {
            CartScreen()
        }

        composable(MyScreens.SignUpScreen.route) {
            SignUpScreen()
        }

        composable(MyScreens.SignInScreen.route) {
            SignInScreen()
        }

    }

}
