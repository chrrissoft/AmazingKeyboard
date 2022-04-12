package com.chrrissoft.amazingkeyboard.uilayer.screens.main.set_theme_settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun SetThemeSettings(image: Painter, modifier: Modifier) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            ImageTheme(image = image)
        }
    }
}

@Composable
fun ImageTheme(image: Painter) {
    Image(painter = image, contentDescription = null)
}