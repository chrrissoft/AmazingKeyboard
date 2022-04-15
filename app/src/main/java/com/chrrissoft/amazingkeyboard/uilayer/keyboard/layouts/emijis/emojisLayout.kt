@file:Suppress("NAME_SHADOWING")

package com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts.emijis

import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.DeleteKey
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.ToggleToLetterLayoutKey

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
fun EmojiLayout(navController: NavHostController, connection: IMEService) {

    val (currentPage, onPageChange) = remember {
        mutableStateOf(EmojiPages.EmoticonsAndEmotionsPage)
    }
    val deleteKeyInteractionSource = remember { MutableInteractionSource() }
    val isPressed by deleteKeyInteractionSource.collectIsPressedAsState()
    val deleteTextHandle = Handler(Looper.getMainLooper())
    var intervalDeleteText: Long = 300

    val interval = object : Runnable {
        override fun run() {
            connection.deleteText()
            deleteTextHandle.postDelayed(this, intervalDeleteText)
            intervalDeleteText = 100
        }
    }

    if (isPressed) {
        deleteTextHandle.post(interval)
        DisposableEffect(Unit) {
            onDispose {
                deleteTextHandle.removeCallbacks(interval)
                intervalDeleteText = 300
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CurrentEmojiPage(
            connection = connection,
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
            DeleteKey(
                Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = deleteKeyInteractionSource,
                        indication = null
                    ) { })
        }
    }
}

