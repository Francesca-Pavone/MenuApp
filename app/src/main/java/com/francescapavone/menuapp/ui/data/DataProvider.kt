package com.francescapavone.menuapp.ui.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
            name = "Bruschette al pomodoro",
            price = 5.50,
            image = R.drawable.bruschette
        )
    /*TODO*/
    )

    val firstCoursesList = listOf(
        Dish(
            id = 2,
            name = "Cozze e pesto di pistacchi",
            price = 12.50,
            image = R.drawable.pasta_cozze_pistacchi
        ),
        Dish(
            id = 3,
            name = "Tonno e zucchine",
            price = 11.00,
            image = R.drawable.pasta_tonno_zucchine
        ),
        Dish(
            id = 4,
            name = "Pasta allo scoglio",
            price = 11.50,
            image = R.drawable.primo_pesce
        ),
        Dish(
            id = 5,
            name = "Pappardelle ai porcini",
            price = 11.00,
            image = R.drawable.pappardelle_porcini
        ),
        Dish(
            id = 6,
            name = "Tagliatelle al ragù",
            price = 10.50,
            image = R.drawable.tagliatelle_ragu
        )
    )

    val secondCourseList = listOf(
        Dish(
            id = 7,
            name = "Tagliata di manzo",
            price = 12.00,
            image = R.drawable.tagliata_manzo
        ),
        Dish(
            id = 8,
            name = "Cotoletta",
            price = 7.50,
            image = R.drawable.cotoletta
        ),
    )

    val sideList = listOf(
        Dish(
            id = 9,
            name = "Patate",
            price = 3.50,
            image = R.drawable.patate
        ),
        Dish(
            id = 10,
            name = "Insalata greca",
            price = 4.00,
            image = R.drawable.insalata_greca
        ),
        Dish(
            id = 11,
            name = "Zucchine grigliate",
            price = 3.00,
            image = R.drawable.zucchine_grigliate
        )
    )

    val fruitList = listOf(
        Dish(
            id = 12,
            name = "Frutta mista",
            price = 4.50,
            image = R.drawable.piatto_misto_frutta
        ),
        Dish(
            id = 13,
            name = "Spiedini di frutta",
            price = 3.50,
            image = R.drawable.spiedini_frutta
        )
    )

    val dessertList = listOf(
        Dish(
            id = 14,
            name = "Panna cotta al mango",
            price = 4.50,
            image = R.drawable.panna_cotta_mango
        ),
        Dish(
            id = 15,
            name = "Mille foglie",
            price = 4.50,
            image = R.drawable.millefoglie
        ),
        Dish(
            id = 16,
            name = "Cheescake alla fragola",
            price = 5.00,
            image = R.drawable.cheesecake
        )
    )

    val drinkList = listOf(
        Dish(
            id = 17,
            name = "Acqua naturale",
            price = 2.00,
            image = R.drawable.acqua_naturale
        ),
        Dish(
            id = 18,
            name = "Acqua frizzante",
            price = 2.00,
            image = R.drawable.acqua_frizzante
        ),
        Dish(
            id = 19,
            name = "Coca-Cola",
            price = 3.00,
            image = R.drawable.coca_cola
        ),
        Dish(
            id = 20,
            name = "Birra Beck's",
            price = 4.00,
            image = R.drawable.becks
        )
    )

/*     starterList,
        firstCoursesList,
        secondCourseList,
        sideList,
        fruitList,
        dessertList,
        drinkList*/

    val orderedList = listOf<Dish>()


}