package com.chrrissoft.amazingkeyboard.uilayer.keyboard.qwerty

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.DeleteKey
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.ShiftKey

@Composable
fun QwertyPage(
    unicodeListUppercase: List<List<String>>,
    unicodeListLowercase: List<List<String>>,
    connection: IMEService,
    modifier: Modifier = Modifier
) {
    val nextShitLower = "z"
    val nextShitUpper = "Z"

    val nextDeleteLower = "m"
    val nextDeleteUpper = "M"

    var currentRow = 0
    var currentKey = 0

    var currentUnicodeList: List<List<String>>
    val isShift = rememberSaveable { mutableStateOf(true) }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(modifier = modifier.fillMaxSize()) {
        currentUnicodeList = if (isShift.value) {
            unicodeListUppercase
        } else unicodeListLowercase

        currentUnicodeList.forEach { row ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                row.forEach { key ->
                    if (key == nextShitLower || key == nextShitUpper) ShiftKey(
                        Modifier
                            .weight(1.5f)
                            .padding(start = 7.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { isShift.value = !isShift.value }
                    )
                    QwertyKey(
                        key = key,
                        connection = connection,
                        currentRow = currentRow,
                        currentKey = currentKey,
                        currentUnicodeList = currentUnicodeList,
                        modifier = Modifier
                            .weight(1f),
                    )
                    if (key == nextDeleteLower || key == nextDeleteUpper) DeleteKey(
                        Modifier
                            .weight(1.5f)
                            .padding(end = 7.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { connection.deleteText() }
                    )
                    currentKey++
                }
            }
            currentRow++
            currentKey = 0
        }
    }
}