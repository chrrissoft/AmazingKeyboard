package com.chrrissoft.amazingkeyboard.uilayer.keyboard.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts.QwertyLayout
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts.SymbolsLayout
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts.emijis.EmojiLayout
import com.chrrissoft.amazingkeyboard.uilayer.theme.AmazingKeyboardTheme

abstract class KeyboardColors {
    abstract val key: Number
    abstract val assent: Number
    abstract val background: Number
    abstract val letter: Number
}

@Composable
fun KeyboardScreen(connection: IMEService) {


/*
var autoTheme = false
var darkTheme = false

// theme
val theme = keyboardViewModel.settingsTheme.observeAsState()

// light mode
val settingsDarkColors = keyboardViewModel.settingsDarkColors.observeAsState()
val settingsDarkStyle = keyboardViewModel.settingsDarkStyle.observeAsState()

// light mode
val settingsLightColors = keyboardViewModel.settingsLightColors.observeAsState()
val settingsLightStyle = keyboardViewModel.settingsLightStyle.observeAsState()


// dark mode colors
val dBackground = settingsDarkColors.value!!.background
val dAssentKey = settingsDarkColors.value!!.assentKey
val dLetter = settingsDarkColors.value!!.letter
val dKey = settingsDarkColors.value!!.key

// dark mode style
val dKeySize = settingsDarkStyle.value!!.keySize
val dKeyRound = settingsDarkStyle.value!!.keyRound
val dLetterSize = settingsDarkStyle.value!!.letterSize
val dLetterWeight = settingsDarkStyle.value!!.letterWeight
val dKeyboardHeight = settingsDarkStyle.value!!.keyboardHeight


// light mode colors
val lBackground = settingsLightColors.value!!.background
val lAssentKey = settingsLightColors.value!!.assentKey
val lLetter = settingsLightColors.value!!.letter
val lKey = settingsLightColors.value!!.key

// light mode style
val lKeySize = settingsDarkStyle.value!!.keySize
val lKeyRound = settingsDarkStyle.value!!.keyRound
val lLetterSize = settingsDarkStyle.value!!.letterSize
val lLetterWeight = settingsDarkStyle.value!!.letterWeight
val lKeyboardHeight = settingsDarkStyle.value!!.keyboardHeight


// dark storage data
data class DarkColors(
override val background: Number = dBackground,
override val assent: Number = dAssentKey,
override val letter: Number = dLetter,
override val key: Number = dKey
) : KeyboardColors()

// light storage data
data class LightColors(
override val background: Number = lBackground,
override val assent: Number = lAssentKey,
override val letter: Number = lLetter,
override val key: Number = lKey
) : KeyboardColors()

var currentColors: KeyboardColors = LightColors()

when (theme.value) {
is AutoTheme -> {
autoTheme = true
currentColors = LightColors()
}
is DarkTheme -> {
currentColors = DarkColors()
}
}
*/


    AmazingKeyboardTheme() {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
                .fillMaxWidth()
                .height(240.dp)
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "qwertyLayout") {
                composable("qwertyLayout") { QwertyLayout(navController, connection) }
                composable("symbolsLayout") { SymbolsLayout(navController, connection) }
                composable("emojiLayout") { EmojiLayout(navController, connection) }
            }
        }
    }
}
