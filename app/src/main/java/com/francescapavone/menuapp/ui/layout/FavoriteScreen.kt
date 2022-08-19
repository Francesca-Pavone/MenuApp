package com.francescapavone.menuapp.ui.layout

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.francescapavone.menuapp.ui.utils.ScreenRouter

@Composable
fun FavoritePage(){
    BackHandler() {
        ScreenRouter.navigateTo(1)
    }
}