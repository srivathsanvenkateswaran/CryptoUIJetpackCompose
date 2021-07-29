package com.srivathsanvenkateswaran.cryptocurrencyapp.models

data class TransactionHistory(
    val transactionID: Int,
    val description: String,
    val amount: Float,
    val currency: String,
    val transactionType: String,
    val transactionDate: String
)
