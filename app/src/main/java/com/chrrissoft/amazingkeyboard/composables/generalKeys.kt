package com.chrrissoft.amazingkeyboard.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Backspace Key
@Composable
fun TestKey(modifier: Modifier = Modifier) {
    Key(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.secondaryVariant)
            .padding(end = 2.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Backspace,
            tint = MaterialTheme.colors.onSecondary,
            contentDescription = null
        )
    }
}

@Composable
fun ComaOrPointKey(char: String, modifier: Modifier = Modifier) {
    Key(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.primary)
    ) { Text(text = char, color = MaterialTheme.colors.onPrimary) }
}

@Composable
fun ToggleToEmojiLayoutKey(modifier: Modifier = Modifier, goToEmojisLayout: () -> Unit) {
    Key(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.primary)
            .clickable { goToEmojisLayout() }
    ) { Text(text = "😎") }
}

@Composable
fun ToggleToSymbolLayoutsKey(modifier: Modifier = Modifier, goToSymbolsLayout: () -> Unit) {
    Key(
        modifier = modifier
            .fillMaxSize()
            .padding(3.6.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.secondaryVariant)
            .clickable { goToSymbolsLayout() },
    ) { Text(text = "?123", fontSize = 15.sp, color = MaterialTheme.colors.onSecondary) }
}

@Composable
fun ShiftKey(modifier: Modifier = Modifier) {
    Key(
        modifier = modifier
            .fillMaxSize()
            .padding(3.6.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.secondaryVariant)
            .padding(start = 2.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.ExpandLess,
            modifier = Modifier.size(55.dp),
            tint = MaterialTheme.colors.onSecondary,
            contentDescription = "Backspace"
        )
    }
}

@Composable
fun ToggleToLetterLayoutKey(modifier: Modifier = Modifier, toggleToLetterLayout: () -> Unit) {
    Key(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.primary)
            .clickable { toggleToLetterLayout() },
    ) { Text(text = "abc", fontSize = 16.sp, color = MaterialTheme.colors.onPrimary) }
}

@Composable
fun IMEActonButton(modifier: Modifier = Modifier) {
    Key(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.secondaryVariant),
    ) {
        Icon(
            imageVector = Icons.Rounded.Send,
            tint = MaterialTheme.colors.onSecondary,
            contentDescription = "Backspace"
        )
    }
}


@Composable
fun Key(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun SpacerKey(modifier: Modifier = Modifier) {
    Key(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(MaterialTheme.colors.primary)
    ) { Text(text = "ES", fontSize = 20.sp, color = MaterialTheme.colors.onPrimary) }
}