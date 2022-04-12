package com.chrrissoft.amazingkeyboard.uilayer.keyboard.emojis

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.datalayer.util.unicodeToString

@Composable
fun EmojiPage(page: List<List<Int>>, connection: IMEService,) {
    var currentEmoji = 0
    var currentRow = 0
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
                        currentEmoji = currentEmoji,
                        currentRow = currentRow,
                        connection = connection,
                        emojisList = page,
                        emoji = unicodeToString(emoji),
                        modifier = Modifier.weight(1f)
                    )
                    currentEmoji++
                }
                currentEmoji = 0
                currentRow++
            }
        }
    }
}