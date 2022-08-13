package com.francescapavone.menuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.francescapavone.menuapp.ui.layout.Cart
import com.francescapavone.menuapp.ui.layout.HomePage
import com.francescapavone.menuapp.ui.layout.Menu
import com.francescapavone.menuapp.ui.data.Dish
import com.francescapavone.menuapp.ui.data.Restaurant
import com.francescapavone.menuapp.ui.theme.MenuAppTheme
import com.francescapavone.menuapp.ui.utils.ScreenRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuAppTheme {
                val restaurant = rememberSaveable {mutableStateOf(Restaurant(0, " ", " ", " ", " "," ", " ", 0, false)) }
                val subtotal = rememberSaveable { mutableStateOf(0.0)}
                val orderList = rememberSaveable { mutableListOf<Dish>() }
                Surface {
                    when(ScreenRouter.currentScreen.value) {
                        1 -> HomePage()
                        2 -> Menu(restaurant, subtotal, orderList)
                        3 -> Cart(subtotal, orderList)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MenuAppTheme {
        Greeting("Android")
    }
}