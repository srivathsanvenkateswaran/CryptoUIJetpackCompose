package com.srivathsanvenkateswaran.cryptocurrencyapp.utils

sealed class Screen(val route: String) {
    object SettingsScreen: Screen("settings_screen")
    object HomeScreen: Screen("home_screen")
    object CryptoDetailScreen: Screen("crypto_detail_screen")
    object TransactionScreen: Screen("transaction_screen")
}
