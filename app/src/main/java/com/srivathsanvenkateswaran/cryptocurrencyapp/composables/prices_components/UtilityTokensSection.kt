package com.srivathsanvenkateswaran.cryptocurrencyapp.composables.prices_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srivathsanvenkateswaran.cryptocurrencyapp.models.TrendingCurrency
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Constants
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.DummyData

@Composable
fun UtilityTokensSection(
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.PADDING_SIDE_VALUE.dp),
    ) {
        Text(
            text = "Utility Tokens",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(Constants.PADDING_SIDE_VALUE.dp))

        LazyColumn {
            itemsIndexed(items = DummyData.utilityTokens) { index: Int, item: TrendingCurrency ->
                CoinRateItem(
                    currency = item,
                    onItemClick = onItemClick
                )
            }
        }
    }
}