package com.chrrissoft.amazingkeyboard.uilayer.keyboard.symbolsAndNumbers

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
fun SymbolsKey(
    key: String,
    connection: IMEService,
    currentSymbolsList: List<List<String>>,
    currentKey: Int,
    currentRow: Int,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Key(modifier = modifier
        .fillMaxSize()
        .padding(4.dp)
        .clip(shape = RoundedCornerShape(7.dp))
        .background(MaterialTheme.colors.primary)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) { connection.sendText(currentSymbolsList[currentRow][currentKey]) }
    ) { Text(text = key, fontSize = 18.sp, color = MaterialTheme.colors.onPrimary) }
}