package com.francescapavone.menuapp.ui.data

import com.francescapavone.menuapp.R

object DataProvider {

    val restaurantList = listOf(

        Restaurant(
            id = 1,
            name = "Cracco",
            address = "Largo Achille Albacini, 8",
            city = "Portofino",
            image = R.drawable.cracco
        ),
        Restaurant(
            id = 2,
            name = "Starbucks",
            address = "Via Iotti, 3",
            city = "Roma",
            image = R.drawable.starbucks
        ),
        Restaurant(
            id = 3,
            name = "Old Wild West",
            address = "Via Roma, 16",
            city = "Torino",
            image = R.drawable.oldwildwest
        ),
        Restaurant(
            id = 4,
            name = "Mizzica",
            address = "Piazza Duca, 9",
            city = "Ragusa",
            image = R.drawable.mizzica
        )
    )
}