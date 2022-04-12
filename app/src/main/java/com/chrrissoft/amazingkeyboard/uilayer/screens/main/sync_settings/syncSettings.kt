package com.chrrissoft.amazingkeyboard.uilayer.screens.main.sync_settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SyncSettings(modifier: Modifier) {
    Box(modifier = modifier) {
        Row() {
            Text(
                text = "Sincronizar configuraciones entre oscuro y claro",
                style = TextStyle(fontWeight = FontWeight(600))
            )
        }
    }
}