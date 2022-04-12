package com.chrrissoft.amazingkeyboard.uilayer.screens.main.set_theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.R

@Composable
fun SetTheme(modifier: Modifier = Modifier) {

    val imageAutoTheme = painterResource(id = R.drawable.ic_baseline_backspace_24)
    val imageDarkTheme = painterResource(id = R.drawable.ic_baseline_backspace_24)
    val imageLightTheme = painterResource(id = R.drawable.ic_baseline_backspace_24)

    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            Theme(imageTheme = imageAutoTheme, "Auto", modifier = Modifier.weight(1f))
            Theme(imageTheme = imageDarkTheme, "Dark", modifier = Modifier.weight(1f))
            Theme(imageTheme = imageLightTheme, "Light", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Theme(imageTheme: Painter, themeName: String, modifier: Modifier) {
    val themeStyle =
        modifier
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(colors.secondaryVariant)
            .fillMaxHeight()

    Column(
        modifier = themeStyle,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = imageTheme, contentDescription = null)
        Text(text = themeName)
    }
}