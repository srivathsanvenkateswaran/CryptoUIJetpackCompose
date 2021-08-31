package com.srivathsanvenkateswaran.cryptocurrencyapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srivathsanvenkateswaran.cryptocurrencyapp.R
import com.srivathsanvenkateswaran.cryptocurrencyapp.composables.prices_components.CurrencyInfoSection
import com.srivathsanvenkateswaran.cryptocurrencyapp.models.TrendingCurrency
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.*
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Constants
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.DummyData

@Composable
fun PortfolioScreen(
    onBackArrowPressed: () -> Unit,
    onCoinSearch: (String) -> Unit
) {
    val currency = DummyData.trendingCurrencies[0]

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = LightGray1
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp)
        ) {
            TopNavigationRow(
                onBackArrowPressed = onBackArrowPressed,
                isStarNeeded = false,
            )

            PortfolioCard()

            SearchField(onCoinSearch = onCoinSearch)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Constants.PADDING_SIDE_VALUE.dp),
                elevation = Constants.ELEVATION_VALUE.dp
            ) {
                CurrencySection()
            }
        }
    }
}

@Composable
private fun CurrencySection() {
    Column(
        modifier = Modifier
            .padding(
                top = Constants.PADDING_SIDE_VALUE.dp,
                start = Constants.PADDING_SIDE_VALUE.dp,
                end = Constants.PADDING_SIDE_VALUE.dp
            )
    ) {
        Spacer(modifier = Modifier.height(Constants.PADDING_SIDE_VALUE.dp))

        DummyData.trendingCurrencies.forEachIndexed { index, currency ->
            CoinItem(currency = currency)
            Divider(
                modifier = Modifier
                    .padding(
                        top = Constants.PADDING_SIDE_VALUE.dp,
                        bottom = if (DummyData.trendingCurrencies.size - 1 > index) {
                            Constants.PADDING_SIDE_VALUE.dp
                        } else {
                            0.dp
                        }
                    )
            )
        }
        
        Spacer(modifier = Modifier.height(Constants.PADDING_SIDE_VALUE.dp))

        DummyData.trendingCurrencies.forEachIndexed { index, currency ->
            CoinItem(currency = currency)
            Divider(
                modifier = Modifier
                    .padding(
                        top = Constants.PADDING_SIDE_VALUE.dp,
                        bottom = if (DummyData.trendingCurrencies.size - 1 > index) {
                            Constants.PADDING_SIDE_VALUE.dp
                        } else {
                            0.dp
                        }
                    )
            )
        }
    }
}

@Composable
private fun CoinItem(currency: TrendingCurrency) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Constants.PADDING_SIDE_VALUE.dp)
    ) {
        CurrencyInfoSection(currency = currency)

        CoinAmountSection(currency = currency)
    }
}


@Composable
private fun CoinAmountSection(currency: TrendingCurrency) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${currency.wallet.crypto}",
                style = TextStyle(
                    fontFamily = RobotoRegular,
                    fontSize = 16.sp,
                    lineHeight = 22.sp
                ),
                color = Color.DarkGray
            )
            Text(
                text = "£${currency.wallet.value}",
                style = TextStyle(
                    fontFamily = RobotoRegular,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                ),
                color = Gray
            )
        }

        Image(
            painter = painterResource(id = R.drawable.right_arrow),
            contentDescription = null,
            modifier = Modifier
                .clipToBounds()
                .padding(start = (Constants.PADDING_SIDE_VALUE * 1.5).dp)
        )
    }
}

@Composable
fun SearchField(
    onCoinSearch: (String) -> Unit
) {
    var textFieldState by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textFieldState,
        onValueChange = {
            textFieldState = it
            onCoinSearch(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        label = {
            Text(text = "Search coins..")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.PADDING_SIDE_VALUE.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        maxLines = 1,
        textStyle = TextStyle(
            fontFamily = RobotoRegular,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )
    )
}

@Composable
private fun PortfolioCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.PADDING_SIDE_VALUE.dp),
        elevation = Constants.ELEVATION_VALUE.dp
    ) {
        Row(
            modifier = Modifier
                .padding(Constants.PADDING_SIDE_VALUE.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PortfolioValueSection()

            Image(
                painter = painterResource(id = R.drawable.crypto_icon),
                contentDescription = "crypto icon",
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
private fun PortfolioValueSection() {
    Column() {
        Spacer(modifier = Modifier.height(Constants.ELEVATION_VALUE.dp))

        Text(
            text = "TOTAL PORTFOLIO VALUE",
            style = Typography.subtitle1,
            color = Gray
        )

        Spacer(modifier = Modifier.height(Constants.ELEVATION_VALUE.dp))

        Text(
            text = "£${DummyData.portfolio.balance}",
            style = TextStyle(
                fontFamily = RobotoBold,
                fontSize = 24.sp,
                lineHeight = 30.sp
            )
        )

        Spacer(modifier = Modifier.height((2 * Constants.ELEVATION_VALUE).dp))
    }
}

@Preview
@Composable
fun PortfolioScreenPreview() {
    PortfolioScreen(
        onBackArrowPressed = {

        },
        onCoinSearch = {

        }
    )
}