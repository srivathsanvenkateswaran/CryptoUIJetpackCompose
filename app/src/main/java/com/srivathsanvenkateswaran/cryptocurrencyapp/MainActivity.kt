package com.srivathsanvenkateswaran.cryptocurrencyapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme {
                TradeModelBottomSheet(
                    onButtonClick = {
                        Toast
                            .makeText(this, "Processing transaction...", Toast.LENGTH_LONG)
                            .show()
                    }
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MainActivityContent(
    coroutineScope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomnavigationBar(
                onItemSelected = {
                    when (it) {
                        NavigationItems.Home -> navController.navigate(Screen.HomeScreen.route)
                        NavigationItems.Portfolio -> navController.navigate(Screen.PortfolioScreen.route)
                        NavigationItems.Prices -> navController.navigate(Screen.PricesScreen.route)
                        NavigationItems.Settings -> navController.navigate(Screen.SettingsScreen.route)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch{
                        modalBottomSheetState.show()
                    }
                },
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


@Composable
@ExperimentalMaterialApi
private fun TradeModelBottomSheet(
    onButtonClick: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue =ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            TradeScreen(
                onButtonClick = {
                    onButtonClick()
                }
            )
        },
        content = {
            MainActivityContent(scope, modalBottomSheetState)
        }
    )
}