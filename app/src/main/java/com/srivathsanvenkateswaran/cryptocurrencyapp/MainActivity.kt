package com.srivathsanvenkateswaran.cryptocurrencyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.srivathsanvenkateswaran.cryptocurrencyapp.composables.SplashScreen
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.CryptocurrencyAppTheme
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.SplashScreen.route
                ) {
                    composable(
                        route = Screen.SplashScreen.route
                    ) {
                        SplashScreen()
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(2000)
                            navController.popBackStack()
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    }
                    composable(
                        route = Screen.HomeScreen.route
                    ) {

                    }
                    composable(
                        route = Screen.CryptoDetailScreen.route
                    ) {

                    }
                    composable(
                        route = Screen.TransactionScreen.route
                    ) {

                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptocurrencyAppTheme {

    }
}