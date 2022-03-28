package com.chrrissoft.amazingkeyboard.composables.emojis

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.composables.Key


@Composable
fun EmojiKey(emoji: String, modifier: Modifier = Modifier) {
    Key(modifier = modifier.padding(4.dp)) { Text(text = emoji) }}
