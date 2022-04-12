package com.chrrissoft.amazingkeyboard.uilayer.keyboard.emojis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.datalayer.util.unicodeToString
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.Key


@Composable
fun EmojiKey(
    emoji: String,
    connection: IMEService,
    currentEmoji: Int,
    currentRow: Int,
    emojisList: List<List<Int>>,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Key(
        modifier = modifier
            .padding(4.dp)
            .clickable(interactionSource = interactionSource, indication = null) {
                connection.sendText(unicodeToString(emojisList[currentRow][currentEmoji]))
            }) {
        Text(
            text = emoji
        )
    }
}
