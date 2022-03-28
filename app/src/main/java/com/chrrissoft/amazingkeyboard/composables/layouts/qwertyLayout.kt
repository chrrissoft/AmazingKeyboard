package com.chrrissoft.amazingkeyboard.composables.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.chrrissoft.amazingkeyboard.composables.*
import com.chrrissoft.amazingkeyboard.composables.qwerty.QwertyPage
import com.chrrissoft.amazingkeyboard.composables.qwerty.qwertyList

@Composable
fun QwertyLayout(navController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        QwertyPage(qwertyList, Modifier.weight(3f))
        BarraInferirQwerty(navController = navController, modifier = Modifier.weight(1f))
    }
}

@Composable
fun BarraInferirQwerty(navController: NavHostController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ToggleToSymbolLayoutsKey(Modifier.weight(1.5f)) { navController.navigate("symbolsLayout") }
        ToggleToEmojiLayoutKey(Modifier.weight(1f)) { navController.navigate("emojiLayout") }
        ComaOrPointKey(",", Modifier.weight(1f))
        SpacerKey(Modifier.weight(3.5f))
        ComaOrPointKey(".", Modifier.weight(1f))
        IMEActonButton(Modifier.weight(1.5f))
    }
}