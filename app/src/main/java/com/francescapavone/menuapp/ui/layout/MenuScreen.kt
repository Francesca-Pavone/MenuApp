package com.francescapavone.menuapp.ui.layout

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francescapavone.menuapp.R
import com.francescapavone.menuapp.ui.components.DishCard
import com.francescapavone.menuapp.ui.data.DataProvider
import com.francescapavone.menuapp.ui.data.Dish
import com.francescapavone.menuapp.ui.data.Restaurant
import com.francescapavone.menuapp.ui.theme.myGreen
import com.francescapavone.menuapp.ui.theme.myYellow
import com.francescapavone.menuapp.ui.utils.ScreenRouter

@Composable
fun Menu(restaurant: MutableState<Restaurant>, subtotal: MutableState<Double>, orderList: MutableList<Dish>){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val starters = rememberSaveable { DataProvider.starterList }
    val firstCourses = rememberSaveable { DataProvider.firstCoursesList }
    val secondCourses = rememberSaveable { DataProvider.secondCourseList }
    val sides = rememberSaveable { DataProvider.sideList }
    val fruits = rememberSaveable { DataProvider.fruitList }
    val desserts = rememberSaveable { DataProvider.dessertList }
    val drinks = rememberSaveable { DataProvider.drinkList }

//    val message = rememberSaveable{ mutableStateOf(if(!restaurant.value.favourite) "Add to favourites" else "Remove from favourites") }
    val favIcon = rememberSaveable { mutableStateOf(if(!restaurant.value.favourite) R.drawable.add_favourite else R.drawable.remove_favourite) }

/*
    val ordersName = listOf("Starters", "First courses", "Second courses", "Sides", "Fruits", "Desserts", "Drinks")
    val orders = rememberSaveable {
        listOf(DataProvider.starterList, DataProvider.firstCoursesList, DataProvider.secondCourseList, DataProvider.sideList, DataProvider.fruitList, DataProvider.dessertList, DataProvider.drinkList)
    }*/
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.bg_app),
        contentDescription = "bg",
        contentScale = ContentScale.FillBounds
    )
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { ScreenRouter.navigateTo(3) },
                modifier = Modifier.size(64.dp) ,
                backgroundColor = myYellow,
                contentColor = myGreen,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(5.dp),
            ) {
                Image(painter = painterResource(id = R.drawable.shop_bag2), contentDescription = "shoppingCart")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        topBar = { CartTopBar(restaurant) }
    ) {
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp)
        ) {

            /*Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .background(color = myYellow, shape = RoundedCornerShape(30))
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        restaurant.value.favourite = !restaurant.value.favourite
                        if (restaurant.value.favourite) message.value =
                            "Remove from favourites" else message.value = "Add to favourites"
                    }
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                    text = message.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Divider(
                    color = myGreen,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Image(
                    modifier = Modifier
                        .padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
                    painter = painterResource(id = if (restaurant.value.favourite) R.drawable.remove_favourite else R.drawable.add_favourite),
                    contentDescription = null,
                )
            }
*/
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                IconButton(
                    onClick = { restaurant.value.favourite = !restaurant.value.favourite
                        if (restaurant.value.favourite) favIcon.value = R.drawable.remove_favourite
                        else  favIcon.value = R.drawable.add_favourite
                    },
                    modifier = Modifier
                        .padding(end = 50.dp)
                        .align(Alignment.CenterVertically)
                        .width(50.dp)
                        .height(50.dp)
                        .background(color = myYellow, shape = RoundedCornerShape(50)))
                {
                    Icon(modifier = Modifier.padding(15.dp), painter = painterResource(id = favIcon.value), tint = myGreen,  contentDescription = "add")
                }
                IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(50.dp)
                        .height(50.dp)
                        .background(color = myYellow, shape = RoundedCornerShape(50)))
                {
                    Icon(modifier = Modifier.padding(15.dp), painter = painterResource(id = R.drawable.download), tint = myGreen,  contentDescription = "add")
                }
            }

            Title(title = "Starters")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(20.dp),
            ){
                items(
                    items = starters,
                    itemContent = {
                        DishCard(dish = it, subtotal =  subtotal, orderList = orderList)
                    }
                )
            }

            Title(title = "First courses")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(20.dp)
            ){
                items(
                    items = firstCourses,
                    itemContent = {
                        DishCard(dish = it, subtotal = subtotal, orderList = orderList)
                    }
                )
            }

            Title(title = "Second courses")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(20.dp)
            ){
                items(
                    items = secondCourses,
                    itemContent = {
                        DishCard(dish = it, subtotal = subtotal, orderList = orderList)
                    }
                )
            }

            Title(title = "Sides")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(20.dp)
            ){
                items(
                    items = sides,
                    itemContent = {
                        DishCard(dish = it, subtotal = subtotal, orderList = orderList)
                    }
                )
            }

            Title(title = "Fruits")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(20.dp)
            ){
                items(
                    items = fruits,
                    itemContent = {
                        DishCard(dish = it, subtotal = subtotal, orderList = orderList)
                    }
                )
            }

            Title(title = "Desserts")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(20.dp)
            ){
                items(
                    items = desserts,
                    itemContent = {
                        DishCard(dish = it, subtotal = subtotal, orderList = orderList)
                    }
                )
            }

            Title(title = "Drinks")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(20.dp)
            ){
                items(
                    items = drinks,
                    itemContent = {
                        DishCard(dish = it, subtotal = subtotal, orderList = orderList)
                    }
                )
            }
            Spacer(modifier = Modifier
                .height(100.dp)
                .fillMaxWidth())
        }

    }
    BackHandler() {
        ScreenRouter.navigateTo(1)
    }
}



@Composable
fun CartTopBar(restaurant: MutableState<Restaurant>){
}

@Composable
fun Title(title: String){
    Divider(
        modifier = Modifier.padding(20.dp),
        color = Color.Black,
        thickness = 1.dp
    )
    Text(
        modifier = Modifier.padding(start = 20.dp, bottom = 10.dp),
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}
