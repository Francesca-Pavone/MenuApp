package com.francescapavone.menuapp.ui.layout

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francescapavone.menuapp.R
import com.francescapavone.menuapp.ui.components.DishCard
import com.francescapavone.menuapp.ui.components.OrderedDishCard
import com.francescapavone.menuapp.ui.components.RestaurantCard
import com.francescapavone.menuapp.ui.data.DataProvider
import com.francescapavone.menuapp.ui.data.Dish
import com.francescapavone.menuapp.ui.data.Restaurant
import com.francescapavone.menuapp.ui.theme.myGreen
import com.francescapavone.menuapp.ui.theme.myYellow
import com.francescapavone.menuapp.ui.utils.ScreenRouter

@Composable
fun HomePage(){
    val restaurantName = rememberSaveable { mutableStateOf("") }

    val restaurants = rememberSaveable { DataProvider.restaurantList }

    val conf = LocalConfiguration.current

    val portrait = when (conf.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            false
        }
        else -> {
            true
        }
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.bg_app),
        contentDescription = "bg",
        contentScale = ContentScale.FillBounds
    )
    Scaffold(
        backgroundColor = Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { ScreenRouter.navigateTo(3) },
                modifier = Modifier.size(64.dp) ,
                backgroundColor = myYellow,
                contentColor = myGreen,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(5.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.shop_bag2), contentDescription = "shoppingCart")
            }
        },
        floatingActionButtonPosition = if(portrait) FabPosition.End else FabPosition.Center,
        isFloatingActionButtonDocked = !portrait,
        bottomBar = { if (!portrait) HomeBottomBar() },
        topBar = { if (portrait) HomeTopBar(restaurantName) },
    ) {paddingValues ->
        if(portrait) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
            ) {
                LazyRow(
                    contentPadding = PaddingValues(start = 110.dp, top = 100.dp),
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    items(
                        items = restaurants,
                        itemContent = {
                            RestaurantCard(restaurant = it)
                        }
                    )
                }
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(start = 15.dp, bottom = 15.dp)
                        .size(64.dp)
                        .align(Alignment.BottomStart),
                    backgroundColor = myGreen,
                    contentColor = myYellow,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(5.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(18.dp),
                        painter = painterResource(id = R.drawable.add_favourite),
                        contentDescription = "Localized description",
                    )
                }

            }
        } else {
            LazyRow(
                contentPadding = PaddingValues(start = 110.dp, top = 10.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                items(
                    items = restaurants,
                    itemContent = {
                        RestaurantCard(restaurant = it)
                    }
                )
            }
        }
    }
}


@Composable
fun HomeTopBar(restaurant: MutableState<String>) {
    Box(modifier = Modifier
        .height(110.dp)){
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.bg_topbar),
            contentDescription = "TopBar Background",
            contentScale = ContentScale.FillBounds
        )
        Row(
            Modifier
                .padding(20.dp, 10.dp, 20.dp, 20.dp)
                .wrapContentHeight()
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = restaurant.value,
                onValueChange = {restaurant.value = it},
                placeholder = { Text(text = "Search a restaurant", fontSize = 16.sp) },
                textStyle = TextStyle(lineHeight = 70.sp),
                maxLines = 1,
                //singleLine = true,
                leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = myGreen
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 18.dp)
            )
            IconButton(onClick = { }) {
                Image(painter = painterResource(id = R.drawable.qr_code), contentDescription = "qrCodeScanner")
            }
        }
    }
}

@Composable
fun HomeBottomBar(){
    BottomAppBar(
        elevation = AppBarDefaults.BottomAppBarElevation,
        cutoutShape = CircleShape,
        backgroundColor = myGreen,
        contentPadding = AppBarDefaults.ContentPadding
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = myYellow
                )
            }
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Rounded.Search,
                contentDescription = null,
                tint = myYellow
            )
        }
    }
}




