package com.srivathsanvenkateswaran.cryptocurrencyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.srivathsanvenkateswaran.cryptocurrencyapp.composables.*
import com.srivathsanvenkateswaran.cryptocurrencyapp.models.TrendingCurrency
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.CryptocurrencyAppTheme
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.Purple500
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.PurpleOne
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.PurpleTwo
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.NavigationItems
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

                Scaffold(
                    bottomBar = {
                        BottomnavigationBar(
                            onItemSelected = {
                                when (it) {
                                    NavigationItems.Home -> navController.navigate(Screen.HomeScreen.route)
                                    NavigationItems.Portfolio -> navController.navigate(Screen.TransactionScreen.route)
                                    NavigationItems.Prices -> navController.navigate(Screen.CryptoDetailScreen.route)
                                    NavigationItems.Settings -> navController.navigate(Screen.SettingsScreen.route)
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {  },
                            backgroundColor = Purple500,
                            contentColor = Color.White,
                            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.transaction),
                                contentDescription = null
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    isFloatingActionButtonDocked = true
                ) {
                    Navigation(navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen() { currencyCode ->
                navController.navigate(Screen.CryptoDetailScreen.route + "/$currencyCode")
            }
        }
        composable(
            route = Screen.CryptoDetailScreen.route + "/{currencyCode}",
            arguments = listOf(
                navArgument(name = "currencyCode") {
                    type = NavType.StringType
                }
            )
        ) {
            val currencyCode = it.arguments?.getString("currencyCode")!!
            CryptoDetailScreen(
                currencyCode = currencyCode,
                onBackArrowPressed = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Screen.TransactionScreen.route
        ) {
            TransactionScreen()
        }
        composable(
            route = Screen.SettingsScreen.route
        ) {
            SettingsScreen()
        }
    }
}