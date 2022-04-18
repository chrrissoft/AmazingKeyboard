package com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.common.*
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.qwerty.QwertyPage
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.qwerty.qwertyListLowercase
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.qwerty.qwertyListUppercase

@Composable
fun QwertyLayout(navController: NavHostController, connection: IMEService) {
    Column(Modifier.fillMaxSize()) {
        QwertyPage(
            unicodeListUppercase = qwertyListUppercase,
            unicodeListLowercase = qwertyListLowercase,
            connection = connection,
            modifier = Modifier.weight(3f)
        )
        BottomBarQwerty(
            navController = navController,
            connection = connection,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun BottomBarQwerty(
    navController: NavHostController,
    connection: IMEService,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }


    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ToggleToSymbolLayoutsKey(Modifier.weight(1.5f)) { navController.navigate("symbolsLayout") }
        ToggleToEmojiLayoutKey(Modifier.weight(1f)) { navController.navigate("emojiLayout") }
        ComaOrPointKey(",",
            Modifier
                .weight(1f)
                .clickable(
                    interactionSource = interactionSource, indication = null
                ) { connection.onKey(",", null) })
        SpacerKey(
            Modifier
                .weight(3.5f)
                .clickable(
                    interactionSource = interactionSource, indication = null
                ) { connection.onKey(" ", null) })
        ComaOrPointKey( ".",
            Modifier
                .weight(1f)
                .clickable(
                    interactionSource = interactionSource, indication = null
                ) { connection.onKey(".", null) })
        IMEActonButton(Modifier.weight(1.5f)) { connection.doneText() }
    }
}