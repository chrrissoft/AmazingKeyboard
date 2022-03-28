package com.chrrissoft.amazingkeyboard.composables.symbolsAndNumbers

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SymbolsPage(page: List<List<String>>) {
    Column(modifier = Modifier.fillMaxSize()) {
        page.forEach { row ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                row.forEach { key ->
                    SymbolsKey(key = key, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}