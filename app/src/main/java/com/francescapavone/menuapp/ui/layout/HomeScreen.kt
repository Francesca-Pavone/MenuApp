package com.francescapavone.menuapp.ui.layout

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francescapavone.menuapp.R
import com.francescapavone.menuapp.api.RestaurantApi
import com.francescapavone.menuapp.model.Course
import com.francescapavone.menuapp.model.RestaurantPreview
import com.francescapavone.menuapp.ui.components.RestaurantCard
import com.francescapavone.menuapp.ui.data.DataProvider
import com.francescapavone.menuapp.ui.theme.myGreen
import com.francescapavone.menuapp.ui.theme.myYellow
import com.francescapavone.menuapp.ui.utils.ScreenRouter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

@Composable
fun HomePage(
    list: SnapshotStateList<RestaurantPreview>,
    starters: SnapshotStateList<Course>,
    firstcourses: SnapshotStateList<Course>,
    secondcourses: SnapshotStateList<Course>,
    sides: SnapshotStateList<Course>,
    fruits: SnapshotStateList<Course>,
    desserts: SnapshotStateList<Course>,
    drinks: SnapshotStateList<Course>
){
    val restaurantName = rememberSaveable { mutableStateOf("") }
    val total = rememberSaveable { mutableStateOf(0) }
    val context = LocalContext.current
    val s = RestaurantApi(context)

    val conf = LocalConfiguration.current

    val searching = rememberSaveable{ mutableStateOf(false) }

    val portrait = when (conf.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            false
        }
        else -> {
            true
        }
    }

    s.listAllPreviews(
        {
            val jo = JSONObject(it)
            try {
                total.value = jo.getInt("totalResults")
                val ja = jo.getJSONArray("Restaurants")
                val sType = object : TypeToken<List<RestaurantPreview>>() {}.type

                val gson = Gson()
                val l = gson.fromJson<List<RestaurantPreview>>(ja.toString(), sType)
                println(l)
                list.clear()
                list.addAll(l)
            } catch (e: JSONException) {
                Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()
            }
        },
        {
            Log.v("IMDB", "KAOS")
        }
    )

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
        bottomBar = { if (!portrait) HomeBottomBar(restaurantName, searching) },
        topBar = { if (portrait) HomeTopBar(restaurantName) },
    ) {paddingValues ->

        val filteredList: List<RestaurantPreview> = list.filter { res ->
            (res.name.uppercase().contains(restaurantName.value.uppercase()))
        }

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
                        items = filteredList,
                        itemContent = {
                            RestaurantCard(restaurantPreview = it,starters,firstcourses,secondcourses, sides, fruits, desserts, drinks)
                        }
                    )
                }
                FloatingActionButton(
                    onClick = { ScreenRouter.navigateTo(4) },
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
                        painter = painterResource(id = R.drawable.add_favorite),
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
                    items = filteredList,
                    itemContent = {
                        RestaurantCard(restaurantPreview = it,starters,firstcourses,secondcourses, sides, fruits, desserts, drinks)
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
                placeholder = { Text(text = stringResource(R.string.search), fontSize = 16.sp) },
                textStyle = TextStyle(lineHeight = 70.sp),
                maxLines = 1,
                //singleLine = true,
                leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = myGreen,
                    textColor = Color.Gray
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
fun HomeBottomBar(restaurant: MutableState<String>, searching: MutableState<Boolean>) {

    BottomAppBar(
        elevation = AppBarDefaults.BottomAppBarElevation,
        cutoutShape = CircleShape,
        backgroundColor = myGreen,
        contentPadding = AppBarDefaults.ContentPadding
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            IconButton(onClick = { ScreenRouter.navigateTo(4) }) {
                Icon(
                    Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = myYellow
                )
            }
        }
        Spacer(Modifier.weight(1f, true))
        if (searching.value) {
            TextField(
                value = restaurant.value,
                onValueChange = { restaurant.value = it },
                modifier = Modifier.height(50.dp),
                placeholder = { Text(text = stringResource(R.string.search)) },
                textStyle = TextStyle(lineHeight = 70.sp),
                maxLines = 1,
                //singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close",
                        modifier = Modifier.clickable { searching.value = false })
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = myGreen,
                    textColor = Color.Gray
                )
            )
        } else {
            IconButton(onClick = { searching.value = !searching.value }) {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null,
                    tint = myYellow
                )
            }
        }
    }
}




