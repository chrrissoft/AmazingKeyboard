package com.chrrissoft.amazingkeyboard.composables.qwerty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chrrissoft.amazingkeyboard.composables.Key

@Composable
fun QwertyKey(key: String, modifier: Modifier = Modifier) {
    Key(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 3.5.dp, vertical = 4.dp)
        .clip(shape = RoundedCornerShape(7.dp))
        .background(MaterialTheme.colors.primary)
    ) { Text(text = key, fontSize = 18.sp, color = MaterialTheme.colors.onPrimary) }
}