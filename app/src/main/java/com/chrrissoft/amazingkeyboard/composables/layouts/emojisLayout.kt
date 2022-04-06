@file:Suppress("NAME_SHADOWING")

package com.chrrissoft.amazingkeyboard.composables.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.chrrissoft.amazingkeyboard.composables.ToggleToLetterLayoutKey
import com.chrrissoft.amazingkeyboard.composables.emojis.*
import com.chrrissoft.amazingkeyboard.composables.layouts.EmojiPages.*
import com.chrrissoft.amazingkeyboard.util.unicodeToString

enum class EmojiPages {
    EmoticonsAndEmotionsPage,
    AnimalsAndNaturePage,
    PeoplesPages,
    ActivitiesAndEventsPage,
    FoodAndDrinkPage,
    PlacesAndEventsPage,
    ObjectsPage,
    SymbolsPage,
}

@Composable
fun EmojiLayout(navController: NavHostController) {

    val (currentPage, onPageChange) = remember {
        mutableStateOf(EmoticonsAndEmotionsPage)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        EmojiPage(
            currentPage = currentPage, modifier = Modifier
                .weight(4f)
                .fillMaxWidth()
        )
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToggleToLetterLayoutKey(Modifier.weight(1f)) { navController.navigate("qwertyLayout") }
            EmojiPagePickers(
                selectedPage = currentPage,
                modifier = Modifier.weight(5f)
            ) { onPageChange(it) }
//            TestKey(Modifier.weight(1f))
        }
    }
}

@Composable
fun EmojiPage(currentPage: EmojiPages, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        when (currentPage) {
            EmoticonsAndEmotionsPage -> {
                EmoticonsAndEmotionsPager()
            }
            PeoplesPages -> {
                PeoplesPager()
            }
            AnimalsAndNaturePage -> {
                AnimalsAndNaturePager()
            }
            FoodAndDrinkPage -> {
                FoodAndDrinkPager()
            }
            PlacesAndEventsPage -> {
                PlacesAndEventsPager()
            }
            ActivitiesAndEventsPage -> {
                ActivitiesAndEventsPager()
            }
            ObjectsPage -> {
                ObjetsPager()
            }
            SymbolsPage -> {
                SymbolsPager()
            }
        }
    }
}

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
            selected = (selectedPage == EmoticonsAndEmotionsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(EmoticonsAndEmotionsPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F9D2),
            selected = (selectedPage == PeoplesPages),
            modifier = Modifier.weight(1f),
        ) { onChangePage(PeoplesPages) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F412),
            selected = (selectedPage == AnimalsAndNaturePage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(AnimalsAndNaturePage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F347),
            selected = (selectedPage == FoodAndDrinkPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(FoodAndDrinkPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F30D),
            selected = (selectedPage == PlacesAndEventsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(PlacesAndEventsPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F383),
            selected = (selectedPage == ActivitiesAndEventsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(ActivitiesAndEventsPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F453),
            selected = (selectedPage == ObjectsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(ObjectsPage) }
        EmojiPagePicker(
            icon = unicodeToString(0x1F3E7),
            selected = (selectedPage == SymbolsPage),
            modifier = Modifier.weight(1f),
        ) { onChangePage(SymbolsPage) }
    }
}

@Composable
fun EmojiPagePicker(
    icon: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onChangePage: () -> Unit
) {
    val color = if (selected) MaterialTheme.colors.secondaryVariant else Color.Transparent
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(7.dp))
            .background(color)
            .clickable(interactionSource = interactionSource, indication = null) { onChangePage() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = icon)
    }
}
