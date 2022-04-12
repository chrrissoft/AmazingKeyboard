package com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts.emijis

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.emojis.*

@Composable
fun CurrentEmojiPage(currentPage: EmojiPages, connection: IMEService, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        when (currentPage) {
            EmojiPages.EmoticonsAndEmotionsPage -> {
                EmoticonsAndEmotionsPager(connection)
            }
            EmojiPages.PeoplesPages -> {
                PeoplesPager(connection)
            }
            EmojiPages.AnimalsAndNaturePage -> {
                AnimalsAndNaturePager(connection)
            }
            EmojiPages.FoodAndDrinkPage -> {
                FoodAndDrinkPager(connection)
            }
            EmojiPages.PlacesAndEventsPage -> {
                PlacesAndEventsPager(connection)
            }
            EmojiPages.ActivitiesAndEventsPage -> {
                ActivitiesAndEventsPager(connection)
            }
            EmojiPages.ObjectsPage -> {
                ObjetsPager(connection)
            }
            EmojiPages.SymbolsPage -> {
                SymbolsPager(connection)
            }
        }
    }
}

