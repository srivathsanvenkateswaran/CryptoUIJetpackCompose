package com.srivathsanvenkateswaran.cryptocurrencyapp.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srivathsanvenkateswaran.cryptocurrencyapp.models.TrendingCurrency
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.Gray
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.Typography
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Constants
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.DummyData
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Screen
import com.srivathsanvenkateswaran.cryptocurrencyapp.R
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.LightGray1

@Composable
fun CryptoDetailScreen(
    currencyCode: String,
    onBackArrowPressed: () -> Unit
) {
    val currency = DummyData.trendingCurrencies.find { it.currencyCode == currencyCode }!!

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = LightGray1
    ) {
        Column() {
            TopNavigationRow(onBackArrowPressed = onBackArrowPressed)

            Card(
                modifier = Modifier
                    .padding(Constants.PADDING_SIDE_VALUE.dp)
                    .fillMaxWidth()
            ) {
                CardCurrencyInfoSection(currency = currency)
            }
        }
    }
}

@Composable
private fun CardCurrencyInfoSection(
    currency: TrendingCurrency
) {
    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Constants.PADDING_SIDE_VALUE.dp)
        ) {
            CurrencyItem(currency = currency)

            Row(
                horizontalArrangement = Arrangement.spacedBy(Constants.PADDING_SIDE_VALUE.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                ValuesItem(
                    currency = currency,
                    changesModifier = Modifier,
                    priceModifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun TopNavigationRow(onBackArrowPressed: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .padding(
                top = (Constants.ELEVATION_VALUE + Constants.PADDING_SIDE_VALUE).dp,
                start = (2 * Constants.ELEVATION_VALUE).dp,
                end = Constants.PADDING_SIDE_VALUE.dp
            )
            .fillMaxWidth()
    ) {
        BackRowItem(onBackArrowPressed = onBackArrowPressed)

        FavouritesStarItem()
    }
}

@Composable
private fun FavouritesStarItem(
    isStartNeeded: Boolean = true
) {
    if (isStartNeeded) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "favourites start",
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}

@Composable
private fun BackRowItem(onBackArrowPressed: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Go back",
            tint = Gray,
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    onBackArrowPressed()
                }
        )

        Text(
            text = "Back",
            modifier = Modifier
                .padding(start = 8.dp),
            style = Typography.h2
        )
    }
}

@Preview
@Composable
fun CyptoDetailScreenPreview() {
    CryptoDetailScreen(currencyCode = "BTC") {
        
    }
}