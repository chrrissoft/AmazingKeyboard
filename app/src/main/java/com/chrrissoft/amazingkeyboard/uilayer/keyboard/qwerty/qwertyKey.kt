package com.chrrissoft.amazingkeyboard.uilayer.keyboard.qwerty

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.Key

@Composable
fun QwertyKey(
    key: String,
    connection: IMEService,
    currentUnicodeList: List<List<String>>,
    currentRow: Int,
    currentKey: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Key(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 3.dp, vertical = 3.5.dp)
        .clip(shape = RoundedCornerShape(7.dp))
        .background(MaterialTheme.colors.primary)
        .clickable(interactionSource = interactionSource, indication = null) {
            connection.sendText(currentUnicodeList[currentRow][currentKey],)
            onClick()
        }
    ) { Text(text = key, fontSize = 18.sp, color = MaterialTheme.colors.onPrimary) }
}