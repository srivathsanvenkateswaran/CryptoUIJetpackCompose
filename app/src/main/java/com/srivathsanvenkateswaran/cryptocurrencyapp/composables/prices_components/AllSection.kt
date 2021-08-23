package com.srivathsanvenkateswaran.cryptocurrencyapp.composables.prices_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srivathsanvenkateswaran.cryptocurrencyapp.models.TrendingCurrency
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Constants
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.DummyData

@Composable
fun AllSection(
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.PADDING_SIDE_VALUE.dp),
    ) {
        Text(
            text = "Following",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(Constants.PADDING_SIDE_VALUE.dp))

        LazyColumn {
            itemsIndexed(items = DummyData.followingTokens) { index: Int, item: TrendingCurrency ->
                CoinRateItem(
                    currency = item,
                    onItemClick = onItemClick
                )
            }
        }

        Spacer(modifier = Modifier.height(Constants.PADDING_SIDE_VALUE.dp))

        Text(
            text = "Cryptocurrencies",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(Constants.PADDING_SIDE_VALUE.dp))

        LazyColumn {
            itemsIndexed(items = DummyData.trendingCurrencies) { index: Int, item: TrendingCurrency ->
                CoinRateItem(
                    currency = item,
                    onItemClick = onItemClick
                )
            }
        }
    }
}