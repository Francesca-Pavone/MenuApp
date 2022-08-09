package com.francescapavone.menuapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francescapavone.menuapp.R
import com.francescapavone.menuapp.ui.components.DishCard
import com.francescapavone.menuapp.ui.components.QuantityCounter
import com.francescapavone.menuapp.ui.components.RestaurantCard
import com.francescapavone.menuapp.ui.data.DataProvider
import com.francescapavone.menuapp.ui.data.Restaurant
import com.francescapavone.menuapp.ui.theme.myGreen
import com.francescapavone.menuapp.ui.theme.myYellow
import com.francescapavone.menuapp.ui.utils.ScreenRouter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomePage(){
    val restaurantName = rememberSaveable { mutableStateOf("") }

    val restaurants = rememberSaveable { DataProvider.restaurantList }


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
                onClick = { /*TODO*/ },
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
        //isFloatingActionButtonDocked = true,
        //bottomBar = { BottomBar() },
        topBar = { TopBar(restaurantName) },
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Recommended",
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            LazyRow(
            contentPadding = PaddingValues(start = 110.dp)
            ) {
                items(
                    items = restaurants,
                    itemContent = {
                        RestaurantCard(restaurant = it)
                    }
                )
            }

            //RestaurantCard()
            /*Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "Your favourites",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = Color.Black
                )
            )
            */
        }
    }
}


@Composable
fun TopBar(restaurant: MutableState<String>) {
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

/*@Composable
fun BottomBar(){
    BottomAppBar(
        elevation = AppBarDefaults.BottomAppBarElevation,
        //modifier = Modifier.background(myGreen, shape = RoundedCornerShape(15.dp, 15.dp)),
        cutoutShape = CircleShape,
        backgroundColor = myGreen,
        contentPadding = AppBarDefaults.ContentPadding
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            IconButton(onClick = { *//* doSomething() *//* }) {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = "Localized description",
                    tint = myYellow
                )
            }
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { *//* doSomething() *//* }) {
            Icon(
                Icons.Rounded.Search,
                contentDescription = "Localized description",
                tint = myYellow
            )
        }
    }
}*/



@Composable
fun Menu(){
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
                onClick = { /*TODO*/ },
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
        drawerContent = {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(80.dp), contentAlignment = Alignment.CenterStart){
                Image(
                    painter = painterResource(id = R.drawable.side_menu),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .width(60.dp)
                        .padding(5.dp)
                ) {
                    Course(image = R.drawable.nachos, description = "Antipasto", id = 0)
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.pasta, description = "Primo", id = 1)
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.fried_chicken, description = "Secondo", id = 2)
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.salad, description = "Contorno", id = 3)
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.fruits, description = "Frutta", id = 4)
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.cupcake, description = "Dolce", id = 5)
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.beverage, description = "Bevande", id = 6)
                }
            }
        },
        drawerBackgroundColor = Color.Transparent,
        drawerElevation = 0.dp,
        drawerGesturesEnabled = true
    ) {
        Image(
            modifier = Modifier
                .offset((-65).dp, 0.dp)
                .clickable(onClick = { scope.launch { if (scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() } })
            ,
            painter = painterResource(id = R.drawable.side_menu),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
        )

/*
        var i = 0

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues( 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(
                items = ordersName
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp, bottom = 10.dp),
                    text = it,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                LazyRow {
                    items(
                        items = orders[i],
                        itemContent = { it1 ->
                            DishCard(dish = it1)
                        }
                    )
                }
                i++
                Divider(
                    modifier = Modifier.padding(20.dp),
                    color = Color.Black,
                    thickness = 1.dp
                )

            }
        }
*/


        Column(modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())) {

            Title(title = "Starters")
            LazyRow{
                items(
                    items = starters,
                    itemContent = {
                        DishCard(dish = it)
                    }
                )
            }

            Title(title = "First courses")
            LazyRow{
                items(
                    items = firstCourses,
                    itemContent = {
                        DishCard(dish = it)
                    }
                )
            }

            Title(title = "Second courses")
            LazyRow{
                items(
                    items = secondCourses,
                    itemContent = {
                        DishCard(dish = it)
                    }
                )
            }

            Title(title = "Sides")
            LazyRow{
                items(
                    items = sides,
                    itemContent = {
                        DishCard(dish = it)
                    }
                )
            }

            Title(title = "Fruits")
            LazyRow{
                items(
                    items = fruits,
                    itemContent = {
                        DishCard(dish = it)
                    }
                )
            }

            Title(title = "Desserts")
            LazyRow{
                items(
                    items = desserts,
                    itemContent = {
                        DishCard(dish = it)
                    }
                )
            }

            Title(title = "Drinks")
            LazyRow{
                items(
                    items = drinks,
                    itemContent = {
                        DishCard(dish = it)
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
fun Course(image: Int, description: String, id: Int){
    IconButton(onClick = { /*TODO*/ }) {
        Image(
            painter = painterResource(id = image),
            contentDescription = description,
            modifier = Modifier
                .clip(CircleShape)
        )
    }
/*    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = Modifier
            .clip(CircleShape)
            .clickable {

            }
    )*/
    Spacer(modifier = Modifier.height(5.dp))
    Text(text = description, color = myYellow, fontSize = 10.sp)
}

@Composable
fun Title(title: String){
    Divider(
        modifier = Modifier.padding(top = 20.dp),
        color = Color.Black,
        thickness = 1.dp
    )
    Text(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}