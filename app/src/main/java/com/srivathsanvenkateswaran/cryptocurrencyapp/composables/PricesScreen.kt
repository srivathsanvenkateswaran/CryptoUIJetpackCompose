package com.srivathsanvenkateswaran.cryptocurrencyapp.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.BannerColor
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.LightGray1
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Constants
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.DummyData

@Composable
fun PricesScreen(
    onBackArrowPressed: () -> Unit,
    onCoinSearch: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = LightGray1
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 50.dp)
        ) {
            TopNavigationRow(
                onBackArrowPressed = onBackArrowPressed,
                isStarNeeded = false
            )

            SearchField(onCoinSearch = onCoinSearch)

            ChipsRow()
        }
    }
}

@Composable
private fun ChipsRow() {
    var selectedIndex by remember {
        mutableStateOf(0)
    }

    LazyRow() {
        items(count = DummyData.topChipsName.size) { index ->
            if (index == 0) {
                Spacer(modifier = Modifier.width(Constants.PADDING_SIDE_VALUE.dp))
            }

            SectionChip(
                text = DummyData.topChipsName[index],
                isSelected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                }
            )
            Spacer(modifier = Modifier.width(Constants.ELEVATION_VALUE.dp))
        }
    }
}

@Composable
private fun SectionChip(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val cardColor = if(isSelected) {
        BannerColor
    } else {
        Color.White
    }

    val cardTextColor = if(isSelected) {
        Color.White
    } else {
        Color.Black
    }

    Card(
        shape = RoundedCornerShape(50.dp),
        backgroundColor = cardColor,
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    horizontal = Constants.ELEVATION_VALUE.dp,
                    vertical = (Constants.PADDING_SIDE_VALUE / 2).dp,
                ),
            color = cardTextColor,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun PricesScreenPreview() {
    PricesScreen(
        onBackArrowPressed = {

        },
        onCoinSearch = {
            
        }
    )
}