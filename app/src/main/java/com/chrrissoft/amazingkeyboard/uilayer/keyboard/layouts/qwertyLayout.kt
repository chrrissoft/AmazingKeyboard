package com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FaceRetouchingOff
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Forward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.TransitEnterexit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.*
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

    val ImeAction = connection.IMEAcction.observeAsState()
    val searchImeIcon = Icons.Rounded.Search
    val enterImeIcon = Icons.Rounded.TransitEnterexit
    val test = Icons.Rounded.TransitEnterexit

    var ImeIcon = Icons.Rounded.DarkMode

    if (ImeAction.value == 3) {
        ImeIcon = searchImeIcon
    } else if (ImeAction.value == 2) {
        ImeIcon = Icons.Default.Facebook
    } else if (ImeAction.value == 6) {
        ImeIcon = Icons.Rounded.Face
    } else if (ImeAction.value == 7) {
        ImeIcon = Icons.Default.Home
    }

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
                ) { connection.sendText(",") })
        SpacerKey(
            Modifier
                .weight(3.5f)
                .clickable(
                    interactionSource = interactionSource, indication = null
                ) { connection.sendText(" ") })
        ComaOrPointKey(".",
            Modifier
                .weight(1f)
                .clickable(
                    interactionSource = interactionSource, indication = null
                ) { connection.sendText(".") })
        IMEActonButton(ImeIcon, Modifier.weight(1.5f)) { connection.doneText() }
    }
}