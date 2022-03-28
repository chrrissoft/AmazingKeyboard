package com.chrrissoft.amazingkeyboard.composables

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
import com.chrrissoft.amazingkeyboard.composables.layouts.EmojiLayout
import com.chrrissoft.amazingkeyboard.composables.layouts.QwertyLayout
import com.chrrissoft.amazingkeyboard.composables.layouts.SymbolsLayout
import com.chrrissoft.amazingkeyboard.ui.theme.AmazingKeyboardTheme

@Composable
fun KeyboardScreen() {

    AmazingKeyboardTheme() {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
                .fillMaxWidth()
                .height(240.dp)
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "qwertyLayout") {
                composable("qwertyLayout") { QwertyLayout(navController) }
                composable("symbolsLayout") { SymbolsLayout(navController) }
                composable("emojiLayout") { EmojiLayout(navController) }
            }
        }
    }
}
