package com.francescapavone.menuapp.ui.data

import com.francescapavone.menuapp.R

object DataProvider {

    val restaurantList = listOf(

        Restaurant(
            id = 1,
            name = "Cracco",
            type = "Italian",
            address = "Largo Achille Albacini, 8",
            city = "Portofino",
            price = "€€€€",
            phone = "02 876774",
            image = R.drawable.cracco
        ),
        Restaurant(
            id = 2,
            name = "Starbucks",
            "--",
            address = "Via Iotti, 3",
            city = "Roma",
            price = "€€",
            phone = "06 276214",
            image = R.drawable.starbucks
        ),
        Restaurant(
            id = 3,
            name = "Old Wild West",
            "--",
            address = "Via Roma, 16",
            city = "Torino",
            price = "€€",
            phone = "09 045004",
            image = R.drawable.oldwildwest
        ),
        Restaurant(
            id = 4,
            name = "Mizzica",
            type = "Italian",
            address = "Piazza Duca, 9",
            city = "Ragusa",
            price = "€€€",
            phone = "09 325066",
            image = R.drawable.mizzica
        )
    )

    val starterList = listOf(
        Dish(
            id = 0,
            name = "Tagliere salumi e formaggi",
            price = 8.50,
            image = R.drawable.tagliere
        ),
        Dish(
            id = 1,
            name = "Bruschete al pomodoro",
            price = 5.50,
            image = R.drawable.bruschette
        )
    /*TODO*/
    )
}