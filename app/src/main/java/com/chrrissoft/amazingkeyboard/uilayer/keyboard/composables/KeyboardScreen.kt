package com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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

@Composable
fun KeyboardScreen(connection: IMEService) {
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
