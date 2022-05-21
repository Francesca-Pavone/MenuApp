package com.francescapavone.menuapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Home
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francescapavone.menuapp.R
import com.francescapavone.menuapp.ui.theme.myGreen
import com.francescapavone.menuapp.ui.theme.myYellow
import com.francescapavone.menuapp.ui.utils.ScreenRouter
import kotlinx.coroutines.launch

@Composable
fun HomePage(){
    val restaurantName = rememberSaveable() { mutableStateOf("") }
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
        Column(modifier = Modifier.padding(paddingValues)) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "Recommended",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = Color.Black
                )
            )
            RestaurantCard()
            /*Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "Your favourites",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = Color.Black
                )
            )
            DishCard()*/
        }
    }
}


@Composable
fun TopBar(restaurant: MutableState<String>) {
    Box(modifier = Modifier
        .fillMaxWidth()
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
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = restaurant.value,
                onValueChange = {restaurant.value = it},
                placeholder = { Text(text = "Search a restaurant", fontSize = 16.sp) },
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
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
            )
            Spacer(modifier = Modifier.width(18.dp))
            IconButton(onClick = { }) {
                Image(painter = painterResource(id = R.drawable.qr_code), contentDescription = "qrCodeScanner")
            }
            /*Spacer(modifier = Modifier.width(12.dp))
            IconButton(onClick = {}) {
                Image(painter = painterResource(id = R.drawable.shop_bag), contentDescription = "shoppingCart")
            }*/
        }
    }
}

@Composable
fun BottomBar(){
    BottomAppBar(
        elevation = AppBarDefaults.BottomAppBarElevation,
        //modifier = Modifier.background(myGreen, shape = RoundedCornerShape(15.dp, 15.dp)),
        cutoutShape = CircleShape,
        backgroundColor = myGreen,
        contentPadding = AppBarDefaults.ContentPadding
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = "Localized description",
                    tint = myYellow
                )
            }
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                Icons.Rounded.Search,
                contentDescription = "Localized description",
                tint = myYellow
            )
        }
    }
}

@Composable
fun RestaurantCard(){
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(20.dp)
            .width(180.dp)
            .height(290.dp),
        elevation = 5.dp
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { ScreenRouter.navigateTo(2) },
                    modifier = Modifier.size(40.dp),
                    backgroundColor = myYellow,
                    contentColor = myGreen,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(3.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye2),
                        tint = myGreen,
                        contentDescription = "eye",
                        modifier = Modifier.padding(6.dp)
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End){

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Image(
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.cracco),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(25.dp)),
            )
            Column(modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp)) {
                Text(
                    text = "Cracco",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Largo Achille Albacini, 8",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = "Portofino",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Composable
fun DishCard(){
    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.padding(start = 20.dp)){
        Box(modifier = Modifier
            .height(230.dp)
            .padding(top = 30.dp),
            contentAlignment = Alignment.TopCenter
        ){
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .padding(10.dp)
                    .width(180.dp),
                elevation = 5.dp
            ) {
                Scaffold(bottomBar = {
                    Row(modifier = Modifier.padding(20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Sashimi",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = "€8.50",
                            style = TextStyle(
                                color = myYellow,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .background(color = myYellow, shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Icon(Icons.Default.Add, tint = myGreen,  contentDescription = null)
                    }
                }
                }) {

                }
            }
        }
        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.sashimi),
            contentDescription = null,
            modifier = Modifier
                .padding(5.dp)
                .clip(CircleShape)
                .size(130.dp)
        )
    }
}

@Composable
fun Menu(){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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
                    Course(image = R.drawable.nachos, description = "Antipasto")
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.pasta, description = "Primo")
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.fried_chicken, description = "Secondo")
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.salad, description = "Contorno")
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.fruits, description = "Frutta")
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.cupcake, description = "Dolce")
                    Spacer(modifier = Modifier.height(15.dp))
                    Course(image = R.drawable.beverage, description = "Bevande")
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
                .clickable ( onClick = { scope.launch { if(scaffoldState.drawerState.isClosed) scaffoldState.drawerState.open() else scaffoldState.drawerState.close() }})
            ,
            painter = painterResource(id = R.drawable.side_menu),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
        )
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "Secondi",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    color = Color.Black
                )
            )
            DishCard()
        }

    }
    BackHandler() {
        ScreenRouter.navigateTo(1)
    }
}

@Composable
fun Course(image: Int, description: String){
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = Modifier
            .clip(CircleShape)
            .clickable { }
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(text = description, color = myYellow, fontSize = 10.sp)
}

