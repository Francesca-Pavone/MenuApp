package com.francescapavone.menuapp.ui.layout

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francescapavone.menuapp.R
import com.francescapavone.menuapp.model.Course
import com.francescapavone.menuapp.ui.components.OrderedDishCard
import com.francescapavone.menuapp.ui.data.Dish
import com.francescapavone.menuapp.ui.theme.myYellow
import com.francescapavone.menuapp.ui.utils.ScreenRouter

@Composable
fun Cart(subtotal: MutableState<Double>, orderList: MutableList</*Dish*/Course> ){

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.bg_app),
        contentDescription = "bg",
        contentScale = ContentScale.FillBounds
    )
    Scaffold(
        backgroundColor = Color.Transparent,
        bottomBar = { CartBottomBar(subtotal = subtotal) },
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = "My cart",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(orderList.size != 0){
                LazyColumn(
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(20.dp),
                ) {
                    items(
                        items = orderList,
                        itemContent = {
                            OrderedDishCard(course = it, subtotal = subtotal, orderList = orderList)
                        }
                    )
                }
            }
        }
    }

    BackHandler() {
        ScreenRouter.navigateTo(2)
    }
}

@Composable
fun CartBottomBar(subtotal: MutableState<Double>){
    val conf = LocalConfiguration.current

    val portrait = when (conf.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            false
        }
        else -> {
            true
        }
    }

    Column(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
            )
            .padding(20.dp)
    ) {
        TextAndPrice(text = "Subtotal", price = subtotal.value, portrait)
        TextAndPrice(text = "Delivery", price = 2.0, portrait)
        Divider(
            modifier = Modifier.padding(vertical = if (portrait) 10.dp else 5.dp),
            color = Color.Black,
            thickness = 1.dp
        )
        TextAndPrice(text = "Total", price = subtotal.value + 2, portrait)
        TextButton(
            modifier = Modifier
                .padding(top = if (portrait) 20.dp else 0.dp)
                .align(Alignment.CenterHorizontally),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(30),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = myYellow,
                contentColor = Color.Black)
        ) {
            Text(
                text = "Confirm",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 70.dp)
            )
            Icon(Icons.Rounded.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
fun TextAndPrice(text: String, price: Double, portrait: Boolean){
    Row(
        modifier = Modifier.padding(vertical = if (portrait) 5.dp else 2.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
            text = text,
            fontSize = 16.sp
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End),
            text = "€ ${String.format("%.2f", price)}" ,
            fontSize = 16.sp
        )
    }
}