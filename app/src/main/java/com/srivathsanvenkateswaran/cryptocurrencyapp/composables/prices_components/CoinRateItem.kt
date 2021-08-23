package com.srivathsanvenkateswaran.cryptocurrencyapp.composables.prices_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srivathsanvenkateswaran.cryptocurrencyapp.models.TrendingCurrency
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.*
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Constants
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.DummyData


@Composable
fun CoinRateItem(
    currency: TrendingCurrency,
    onItemClick: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Constants.PADDING_SIDE_VALUE.dp,
                vertical = (Constants.PADDING_SIDE_VALUE / 2).dp
            )
            .clickable {
                onItemClick(currency.currencyCode)
            }
    ) {
        CurrencyInfoSection(currency = currency)

        CoinAmountSection(currency = currency)
    }
}


@Composable
private fun CurrencyInfoSection(currency: TrendingCurrency) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = currency.imageRes),
            contentDescription = "Transaction image",
            modifier = Modifier
                .padding(end = (Constants.PADDING_SIDE_VALUE * 1.5).dp)
        )

        Column {
            Text(
                text = currency.currencyName,
                style = Typography.h4,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = currency.currencyCode,
                style = Typography.h5,
                color = Gray
            )
        }
    }
}

@Composable
private fun CoinAmountSection(currency: TrendingCurrency) {

    val operator by remember {
        mutableStateOf(
            if(currency.changeType == "I") {
                '+'
            } else {
                '-'
            }
        )
    }

    val changesColor by remember {
        mutableStateOf(
            if(currency.changeType == "I") {
                Color.Green
            } else {
                Color.Red
            }
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "£${currency.currentPrice}",
                style = TextStyle(
                    fontFamily = RobotoRegular,
                    fontSize = 16.sp,
                    lineHeight = 22.sp
                ),
                color = Color.Black
            )

            Text(
                text = "$operator${currency.changes}%",
                style = TextStyle(
                    fontFamily = RobotoBold,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                ),
                color = changesColor
            )
        }
    }
}