package com.chrrissoft.amazingkeyboard.uilayer.screens.main.set_theme_settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.R

@Composable
fun SetThemeSettings(image: Painter, modifier: Modifier) {
    Box(modifier.fillMaxSize()) {
        SettingsImageTheme(Modifier.align(Alignment.BottomEnd))
        ImageTheme(image = image, Modifier.align(Alignment.TopStart))
    }
}

@Composable
fun ImageTheme(image: Painter, modifier: Modifier) {
    Box(modifier) {
        Image(
            painter = image, contentDescription = null,
            modifier = Modifier.height(230.dp).width(230.dp).padding(top = 10.dp, start = 10.dp)
        )
    }
}

@Composable
fun SettingsImageTheme(modifier: Modifier) {
    Box(modifier) {
        Image(
            painter = painterResource(id = R.drawable.settings_theme),
            contentDescription = null,
            modifier = Modifier.width(135.dp).height(135.dp).padding(end = 13.dp, bottom = 8.dp)
        )
    }
}