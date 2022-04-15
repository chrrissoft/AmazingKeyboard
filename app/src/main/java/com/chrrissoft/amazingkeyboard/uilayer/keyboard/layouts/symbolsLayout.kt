package com.chrrissoft.amazingkeyboard.uilayer.keyboard.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material.icons.rounded.GMobiledata
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.IMEActonButton
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.SpacerKey
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.composables.ToggleToLetterLayoutKey
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.symbolsAndNumbers.CurrentSymbolsPage
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.symbolsAndNumbers.symbolsListOne
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.symbolsAndNumbers.symbolsListTwo

enum class SymbolsPages {
    PageOne,
    PageTwo
}

@Composable
fun SymbolsLayout(navController: NavHostController, connection: IMEService) {

    val (currentPage, onPageChange) = remember { mutableStateOf(SymbolsPages.PageOne) }

    Column(Modifier.fillMaxSize()) {
        CurrentSymbolsPage(
            currentPage = currentPage, connection = connection, modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
        )

        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToggleToLetterLayoutKey(Modifier.weight(1.5f)) { navController.navigate("qwertyLayout") }
            ChangePageButton(icon = Icons.Rounded.ArrowLeft, modifier = Modifier.weight(1f)) {
                onPageChange(SymbolsPages.PageOne)
            }
            SpacerKey(Modifier.weight(4f).clickable { connection.sendText(" ") })
            ChangePageButton(icon = Icons.Rounded.ArrowRight, modifier = Modifier.weight(1f)) {
                onPageChange(SymbolsPages.PageTwo)
            }
            IMEActonButton(Icons.Rounded.GMobiledata, Modifier.weight(1.5f)) { connection.doneText() }
        }
    }
}

@Composable
fun CurrentSymbolsPage(
    currentPage: SymbolsPages,
    connection: IMEService,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when (currentPage) {
            SymbolsPages.PageOne -> {
                SymbolsPageOne(connection)
            }
            SymbolsPages.PageTwo -> {
                SymbolsPageTwo(connection)
            }
        }
    }
}

@Composable
fun SymbolsPageTwo(connection: IMEService) {
    CurrentSymbolsPage(page = symbolsListTwo, connection = connection)
}

@Composable
fun SymbolsPageOne(connection: IMEService) {
    CurrentSymbolsPage(page = symbolsListOne, connection = connection)
}

@Composable
fun ChangePageButton(icon: ImageVector, modifier: Modifier = Modifier, onChangePage: () -> Unit) {
    Box(
        modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.primary)
            .clickable { onChangePage() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            modifier = Modifier.size(35.dp),
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = null
        )
    }
}