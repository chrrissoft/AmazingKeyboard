package com.chrrissoft.amazingkeyboard.uilayer.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.R
import com.chrrissoft.amazingkeyboard.uilayer.screens.main.set_theme.SetTheme
import com.chrrissoft.amazingkeyboard.uilayer.screens.main.set_theme_settings.SetThemeSettings
import com.chrrissoft.amazingkeyboard.uilayer.screens.main.sync_settings.SyncSettings

@Composable
fun MainScreen() {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(shape = RoundedCornerShape(12.dp))
        .background(MaterialTheme.colors.primary)
    val setThemeSettingsImage = painterResource(id = R.drawable.ic_baseline_backspace_24)

    Column(Modifier.fillMaxSize()) {
//        SetTheme(modifier = modifier.weight(1.5f))
        Try(Modifier.weight(2f).fillMaxSize())
        SetThemeSettings(image = setThemeSettingsImage, modifier = modifier.weight(2.5f))
        SetThemeSettings(image = setThemeSettingsImage, modifier = modifier.weight(2.5f))
        SyncSettings(modifier = modifier.weight(1f))
    }
}

@Composable
fun Try(modifier: Modifier) {
    Box(modifier) {
        val (text, setValue) = remember { mutableStateOf(TextFieldValue("Try here")) }
        TextField(value = text, onValueChange = setValue)
    }
}
