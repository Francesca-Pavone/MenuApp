package com.francescapavone.menuapp.ui.model

data class Restaurant(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val image: Int = 0
)
