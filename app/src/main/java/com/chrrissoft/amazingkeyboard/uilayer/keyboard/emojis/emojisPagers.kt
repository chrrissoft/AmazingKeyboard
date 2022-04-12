package com.chrrissoft.amazingkeyboard.uilayer.keyboard.emojis

import androidx.compose.runtime.Composable
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.datalayer.unicodeEmojis.*

@Composable
fun ActivitiesAndEventsPager(connection: IMEService) {
    val activitiesAndEvents = listOf(
        activitiesAndEventsPage1,
        activitiesAndEventsPage2,
        activitiesAndEventsPage3,
    )
    EmojiPager(activitiesAndEvents, connection)
}

@Composable
fun AnimalsAndNaturePager(connection: IMEService) {
    val animalsAndNature = listOf(
        animalsAndNaturePage1,
        animalsAndNaturePage2,
        animalsAndNaturePage3,
        animalsAndNaturePage4,
        animalsAndNaturePage5,
        animalsAndNaturePage6,
    )
    EmojiPager(animalsAndNature, connection)
}

@Composable
fun EmoticonsAndEmotionsPager(connection: IMEService) {
    val emoticonsAndEmotions = listOf(
        emoticonsAndEmotionsPage1,
        emoticonsAndEmotionsPage2,
        emoticonsAndEmotionsPage3,
        emoticonsAndEmotionsPage4,
        emoticonsAndEmotionsPage5,
        emoticonsAndEmotionsPage6,
        emoticonsAndEmotionsPage7,
    )
    EmojiPager(emoticonsAndEmotions, connection)
}

@Composable
fun FoodAndDrinkPager(connection: IMEService) {
    val foodAndDrinkPager = listOf(
        foodAndDrinkPage1,
        foodAndDrinkPage2,
        foodAndDrinkPage3,
        foodAndDrinkPage4,
    )
    EmojiPager(foodAndDrinkPager, connection)
}

@Composable
fun ObjetsPager(connection: IMEService) {
    val objets = listOf(
        objetsPage1,
        objetsPage2,
        objetsPage3,
        objetsPage4,
        objetsPage5,
        objetsPage6,
        objetsPage7,
    )
    EmojiPager(objets, connection)
}

@Composable
fun PeoplesPager(connection: IMEService) {
    val peoples = listOf(
        peoplesPage1,
        peoplesPage2,
        peoplesPage3,
    )
    EmojiPager(peoples, connection)
}

@Composable
fun PlacesAndEventsPager(connection: IMEService) {
    val placesAndEvents = listOf(
        placesAndEventsPage1,
        placesAndEventsPage2,
        placesAndEventsPage3,
        placesAndEventsPage4,
        placesAndEventsPage5,
        placesAndEventsPage6
    )
    EmojiPager(placesAndEvents, connection)
}

@Composable
fun SymbolsPager(connection: IMEService) {
    val symbols = listOf(
        symbolsPage1,
        symbolsPage2,
        symbolsPage3,
        symbolsPage4,
        symbolsPage5
    )
    EmojiPager(symbols, connection)
}