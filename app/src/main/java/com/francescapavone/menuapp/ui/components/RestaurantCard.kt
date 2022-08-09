package com.francescapavone.menuapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francescapavone.menuapp.R
import com.francescapavone.menuapp.ui.data.Restaurant
import com.francescapavone.menuapp.ui.theme.myGreen
import com.francescapavone.menuapp.ui.theme.myYellow
import com.francescapavone.menuapp.ui.utils.ScreenRouter

@Composable
fun RestaurantCard(restaurant: Restaurant) {

    val moreInfo = rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(end = 20.dp)
            .width(180.dp),
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                alignment = Alignment.Center,
                painter = painterResource(id = restaurant.image),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(25.dp)),
            )
            Column(
                modifier = Modifier
//                    .weight(1f)
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = restaurant.name,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = restaurant.type,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                if(!moreInfo.value) {
                    Text(
                        text = "More info",
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable { moreInfo.value = true }
                            .padding(top = 5.dp),
                        fontSize = 14.sp,
                        color = myGreen
                    )
                }else{
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                    Text(
                        text = restaurant.address,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                    Text(
                        text = restaurant.city,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                    Text(
                        text = restaurant.phone,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                    Text(
                        text = restaurant.price,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Less info",
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable { moreInfo.value = false }
                            .padding(top = 5.dp),
                        fontSize = 14.sp,
                        color = myGreen
                    )
                }

            }

            FloatingActionButton(
                onClick = { ScreenRouter.navigateTo(2) },
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End),
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

        }
    }
}
