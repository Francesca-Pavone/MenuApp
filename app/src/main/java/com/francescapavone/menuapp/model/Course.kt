package com.francescapavone.menuapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    val name: String,
    var price: String,
    var poster: String,
    var description: String
): Parcelable {
    constructor() : this("", "", "","")
}
