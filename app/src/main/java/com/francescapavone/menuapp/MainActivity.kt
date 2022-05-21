package com.francescapavone.menuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.francescapavone.menuapp.ui.HomePage
import com.francescapavone.menuapp.ui.Menu
import com.francescapavone.menuapp.ui.theme.MenuAppTheme
import com.francescapavone.menuapp.ui.utils.ScreenRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            when(ScreenRouter.currentScreen.value) {
                1 -> HomePage()
                2 -> Menu()
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