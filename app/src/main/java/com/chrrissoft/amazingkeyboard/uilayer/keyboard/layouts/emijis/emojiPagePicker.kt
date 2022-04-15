package com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts.emijis

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EmojiPagePicker(
    icon: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onChangePage: () -> Unit
) {
    val color = if (selected) MaterialTheme.colors.secondaryVariant else Color.Transparent
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(7.dp))
            .background(color)
            .clickable(interactionSource = interactionSource, indication = null) { onChangePage() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = icon)
    }
}
