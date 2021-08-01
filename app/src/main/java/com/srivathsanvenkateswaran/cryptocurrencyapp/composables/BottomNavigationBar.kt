package com.srivathsanvenkateswaran.cryptocurrencyapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srivathsanvenkateswaran.cryptocurrencyapp.ui.theme.*
import com.srivathsanvenkateswaran.cryptocurrencyapp.utils.NavigationItems

@Composable
fun BottomnavigationBar(
    modifier: Modifier = Modifier,
    onItemSelected: (NavigationItems) -> Unit
) {
    val navigationItems = listOf<NavigationItems>(
        NavigationItems.Home,
        NavigationItems.Portfolio,
        NavigationItems.Transaction,
        NavigationItems.Prices,
        NavigationItems.Settings
    )

    var selectedNavItemIndex by remember {
        mutableStateOf(0)
    }

    BottomNavigation(
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                shape = RectangleShape
            ),
        contentColor = Purple500,
        backgroundColor = Color.White,
    ) {
        navigationItems.forEach{
            BottomNavigationItem(
                selected = (selectedNavItemIndex == navigationItems.indexOf(it)),
                onClick = {
                    selectedNavItemIndex = navigationItems.indexOf(it)
                    onItemSelected(it)
                },
                icon = {
                    if (it.name == NavigationItems.Transaction.name) {

                    } else {
                        Icon(
                            painter = painterResource(id = it.iconRes),
                            contentDescription = it.name
                        )
                    }
                },
                label = {
                    Text(
                        text = it.name,
                        style = Typography.h5
                    )
                },
                alwaysShowLabel = true,
                selectedContentColor = Purple500,
                unselectedContentColor = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomnavigationBar() {

    }
}