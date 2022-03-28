package com.chrrissoft.amazingkeyboard.composables.emojis

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.util.unicodeToString

@Composable
fun EmojiPage(page: List<List<Int>>) {
    Column(modifier = Modifier.fillMaxSize()) {
        page.forEach { row ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                row.forEach { emoji ->
                    EmojiKey(
                        emoji = unicodeToString(emoji),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}