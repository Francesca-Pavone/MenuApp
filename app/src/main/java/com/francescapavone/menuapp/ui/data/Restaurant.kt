package com.francescapavone.menuapp.ui.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val id: Int,
    val name: String,
    val type: String,
    val address: String,
    val city: String,
    val price: String,
    val phone: String,
    val image: Int,
    var favourite: Boolean = false
) : Parcelable
