package com.srivathsanvenkateswaran.cryptocurrencyapp.composables.trade_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.RepeatOneOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.Gray
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.LightGray1
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.RobotoRegular
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.Constants
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.DummyData

@Composable
fun RepeatSection() {
    TransactSection()

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.PADDING_SIDE_VALUE.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = LightGray1)
        ) {
            DummyData.repeatOptions.forEachIndexed{ index, repeatOptions ->
                RepeatOptionsItem(
                    iconImageVector = repeatOptions.iconImageVector,
                    optionName = repeatOptions.optionName,
                    frequency = repeatOptions.frequency
                )
            }
        }
    }
}

@Preview
@Composable
fun RepeatSectionPreview() {
    Surface() {
        Column() {
            RepeatSection()
        }
    }
}