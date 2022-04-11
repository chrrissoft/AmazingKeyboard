package com.chrrissoft.amazingkeyboard.composables.qwerty

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.IMEService
import com.chrrissoft.amazingkeyboard.composables.TestKey
import com.chrrissoft.amazingkeyboard.composables.ShiftKey

@Composable
fun QwertyPage(page: List<List<String>>, modifier: Modifier = Modifier, connection: IMEService) {
    val nextShit = "z"
    val nextDelete = "m"
    Column(modifier = modifier.fillMaxSize()) {
        page.forEach { row ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                row.forEach { key ->
                    if (key == nextShit) ShiftKey(Modifier.weight(1.5f).padding(start = 7.dp))
                    QwertyKey(key = key, modifier = Modifier.weight(1f))
                    if (key == nextDelete) TestKey(Modifier.weight(1.5f).padding(end = 7.dp), connection)
                }
            }
        }
    }
}