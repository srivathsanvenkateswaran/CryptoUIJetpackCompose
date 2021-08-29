package com.srivathsanvenkateswaran.cryptocurrencyapp.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srivathsanvenkateswaran.cryptocurrencyapp.composables.prices_components.CoinRateItem
import com.srivathsanvenkateswaran.cryptocurrencyapp.composables.prices_components.CurrencyInfoSection
import com.srivathsanvenkateswaran.cryptocurrencyapp.composables.trade_components.LimitSection
import com.srivathsanvenkateswaran.cryptocurrencyapp.composables.trade_components.RepeatSection
import com.srivathsanvenkateswaran.cryptocurrencyapp.composables.trade_components.TransactSection
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.BannerColor
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.Gray
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.RobotoBold
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.RobotoRegular
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Constants
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.DummyData
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Screen

@Composable
fun TradeScreen(
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        var selectedIndex = mutableStateOf(1)

        selectedIndex.value = SectionSelectorRow()

        when(selectedIndex.value) {
            0 -> TransactSection()
            1 -> RepeatSection()
            2 -> LimitSection()
        }
        
        Button(
            onClick = {
                onButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding((2 * Constants.PADDING_SIDE_VALUE).dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = BannerColor
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(
                text = "Trade",
                color = Color.White
            )
        }
    }
}

@Composable
private fun SectionSelectorRow(): Int {
    var selectedSectionIndex by remember {
        mutableStateOf(2)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.PADDING_SIDE_VALUE.dp)
            .clip(RoundedCornerShape((2 * Constants.ELEVATION_VALUE).dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DummyData.tradeScreenSections.forEachIndexed { index, sectionName ->
            SectionCard(
                isSelected = selectedSectionIndex == index,
                text = sectionName,
                modifier = Modifier
                    .weight(1f),
                onCardClick = {
                    selectedSectionIndex = index
                }
            )
        }
    }

    return selectedSectionIndex
}

@Composable
private fun SectionCard(
    isSelected: Boolean = false,
    text: String,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit
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
        shape = RoundedCornerShape((2 * Constants.ELEVATION_VALUE).dp),
        backgroundColor = cardColor,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onCardClick)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    vertical = (Constants.PADDING_SIDE_VALUE / 2).dp,
                ),
            color = cardTextColor,
            fontSize = 14.sp,
            fontFamily = RobotoBold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun TradeScreenPreview() {
    Surface() {
        TradeScreen(
            onButtonClick = {

            }
        )
    }
}