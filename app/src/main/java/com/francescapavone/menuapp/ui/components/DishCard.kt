package com.francescapavone.menuapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francescapavone.menuapp.ui.data.Dish
import com.francescapavone.menuapp.ui.theme.myYellow

@Composable
fun DishCard(dish: Dish, subtotal: MutableState<Double>, orderList: MutableList<Dish> ) {
    val (count, updateCount) = rememberSaveable { mutableStateOf(dish.count) }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(start = 20.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(top = 30.dp)
                .width(180.dp),
            shape = RoundedCornerShape(14.dp),
            elevation = 5.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(top = 100.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = dish.name,
                    modifier = Modifier.padding(top = 5.dp),
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Text(
                    text = "€${dish.price}0",
                    color = myYellow,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                QuantityCounter(
                    modifier = Modifier
                        .align(Alignment.End),
                    count = count,
                    remove = {
                        if (count > 0) {
                            updateCount(count - 1)
                            dish.count = count
                            subtotal.value = subtotal.value - dish.price
                        }else{
                            orderList.remove(dish)
                        }
                    },
                    add = {
                        if (count == 0)
                            orderList.add(dish)
                        updateCount(count + 1)
                        dish.count = count
                        subtotal.value = subtotal.value + dish.price

                    }
                )
                /*IconButton(
                onClick = { },
                modifier = Modifier
                    .background(color = myYellow, shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Icon(Icons.Default.Add, tint = myGreen,  contentDescription = null)
            }*/
            }

        }
        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = dish.image),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(130.dp)
        )
    }
}

@Composable
fun OrderedDishCard(dish: Dish, subtotal: MutableState<Double>, orderList: MutableList<Dish>){
    val (count, updateCount) = rememberSaveable { mutableStateOf(dish.count) }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(start = 20.dp)
    ){
        Card(
            modifier = Modifier
                .padding(end = 30.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(14.dp),
            elevation = 5.dp
        ){
           Row(
               horizontalArrangement = Arrangement.Start,
               modifier = Modifier
                   .padding(end = 100.dp, start = 20.dp, top = 10.dp, bottom = 10.dp)

           ) {
               Image(
                   alignment = Alignment.Center,
                   painter = painterResource(id = dish.image),
                   contentDescription = null,
                   modifier = Modifier
                       .clip(CircleShape)
                       .size(90.dp)
               )
               Column(
                   horizontalAlignment = Alignment.Start,
                   modifier = Modifier.padding(start = 10.dp)
               ) {
                   Text(
                       text = dish.name,
                       modifier = Modifier.padding(top = 5.dp),
                       color = Color.Black,
                       fontSize = 16.sp
                   )
                   Text(
                       modifier = Modifier.padding(top = 10.dp),
                       text = "€${dish.price}0",
                       color = myYellow,
                       fontSize = 18.sp,
                       fontWeight = FontWeight.Bold
                   )
               }
           }
        }
        QuantityCounter(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(color = myYellow, shape = RoundedCornerShape(50))
                .padding(5.dp),
            count = count,
            remove = {
                if (count > 0){
                    updateCount(count - 1)
                    dish.count = count
                    subtotal.value = subtotal.value - dish.price
                }else{
                    orderList.remove(dish)
                }
            },
            add = {
                if(!orderList.contains(dish))
                    orderList.add(dish)
                updateCount(count + 1)
                dish.count = count
                subtotal.value = subtotal.value + dish.price
            }
        )
    }
}
