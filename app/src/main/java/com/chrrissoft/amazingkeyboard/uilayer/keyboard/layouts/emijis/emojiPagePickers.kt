package com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts.emijis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.datalayer.util.unicodeToString

@Composable
fun EmojiPagePickers(
    selectedPage: EmojiPages,
    modifier: Modifier = Modifier,
    onChangePage: (page: EmojiPages) -> Unit
) {
    val selectedPage: EmojiPages = selectedPage
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        EmojiPagePicker(
            icon = unicodeToString(0x1F600),
            selected = (selectedPage == EmojiPages.EmoticonsAndEmotionsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmojiPages.EmoticonsAndEmotionsPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F9D2),
            selected = (selectedPage == EmojiPages.PeoplesPages),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmojiPages.PeoplesPages) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F412),
            selected = (selectedPage == EmojiPages.AnimalsAndNaturePage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmojiPages.AnimalsAndNaturePage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F347),
            selected = (selectedPage == EmojiPages.FoodAndDrinkPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmojiPages.FoodAndDrinkPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F30D),
            selected = (selectedPage == EmojiPages.PlacesAndEventsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmojiPages.PlacesAndEventsPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F383),
            selected = (selectedPage == EmojiPages.ActivitiesAndEventsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmojiPages.ActivitiesAndEventsPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F453),
            selected = (selectedPage == EmojiPages.ObjectsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmojiPages.ObjectsPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F3E7),
            selected = (selectedPage == EmojiPages.SymbolsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmojiPages.SymbolsPage) }
    }
}