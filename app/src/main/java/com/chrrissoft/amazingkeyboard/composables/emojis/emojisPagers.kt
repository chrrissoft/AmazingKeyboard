package com.chrrissoft.amazingkeyboard.composables.emojis

import androidx.compose.runtime.Composable
import com.chrrissoft.amazingkeyboard.unicodeEmojis.*

@Composable
fun ActivitiesAndEventsPager() {
    val activitiesAndEvents = listOf(
        activitiesAndEventsPage1,
        activitiesAndEventsPage2,
        activitiesAndEventsPage3,
    )
    EmojiPager(activitiesAndEvents)
}

@Composable
fun AnimalsAndNaturePager() {
    val animalsAndNature = listOf(
        animalsAndNaturePage1,
        animalsAndNaturePage2,
        animalsAndNaturePage3,
        animalsAndNaturePage4,
        animalsAndNaturePage5,
        animalsAndNaturePage6,
    )
    EmojiPager(animalsAndNature)
}

@Composable
fun EmoticonsAndEmotionsPager() {
    val emoticonsAndEmotions = listOf(
        emoticonsAndEmotionsPage1,
        emoticonsAndEmotionsPage2,
        emoticonsAndEmotionsPage3,
        emoticonsAndEmotionsPage4,
        emoticonsAndEmotionsPage5,
        emoticonsAndEmotionsPage6,
        emoticonsAndEmotionsPage7,
    )
    EmojiPager(emoticonsAndEmotions)
}

@Composable
fun FoodAndDrinkPager() {
    val foodAndDrinkPager = listOf(
        foodAndDrinkPage1,
        foodAndDrinkPage2,
        foodAndDrinkPage3,
        foodAndDrinkPage4,
    )
    EmojiPager(foodAndDrinkPager)
}

@Composable
fun ObjetsPager() {
    val objets = listOf(
        objetsPage1,
        objetsPage2,
        objetsPage3,
        objetsPage4,
        objetsPage5,
        objetsPage6,
        objetsPage7,
    )
    EmojiPager(objets)
}

@Composable
fun PeoplesPager() {
    val peoples = listOf(
        peoplesPage1,
        peoplesPage2,
        peoplesPage3,
    )
    EmojiPager(peoples)
}

@Composable
fun PlacesAndEventsPager() {
    val placesAndEvents = listOf(
        placesAndEventsPage1,
        placesAndEventsPage2,
        placesAndEventsPage3,
        placesAndEventsPage4,
        placesAndEventsPage5,
        placesAndEventsPage6
    )
    EmojiPager(placesAndEvents)
}

@Composable
fun SymbolsPager() {
    val symbols = listOf(
        symbolsPage1,
        symbolsPage2,
        symbolsPage3,
        symbolsPage4,
        symbolsPage5
    )
    EmojiPager(symbols)
}