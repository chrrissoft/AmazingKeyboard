package com.chrrissoft.amazingkeyboard.uilayer.keyboard.symbolsAndNumbers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService

@Composable
fun CurrentSymbolsPage(page: List<List<String>>, connection: IMEService) {
    var currentKey = 0
    var currentRow = 0

    Column(modifier = Modifier.fillMaxSize()) {
        page.forEach { row ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                row.forEach { key ->
                    SymbolsKey(
                        key = key,
                        connection = connection,
                        currentKey = currentKey,
                        currentRow = currentRow,
                        currentSymbolsList = page,
                        modifier = Modifier
                            .weight(1f))
                    currentKey++
                }
            }
            currentRow++
            currentKey = 0
        }
    }
}