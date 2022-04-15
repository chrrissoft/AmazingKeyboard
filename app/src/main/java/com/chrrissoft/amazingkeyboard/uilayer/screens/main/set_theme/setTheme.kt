package com.chrrissoft.amazingkeyboard.uilayer.screens.main.set_theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.R
import com.chrrissoft.amazingkeyboard.datalayer.datastore.AutoTheme
import com.chrrissoft.amazingkeyboard.datalayer.datastore.DarkTheme
import com.chrrissoft.amazingkeyboard.datalayer.datastore.LightTheme
import com.chrrissoft.amazingkeyboard.datalayer.datastore.Theme

@Composable
fun SetTheme(currentTheme: Theme, modifier: Modifier = Modifier) {

    val imageAutoTheme = painterResource(id = R.drawable.keyboard_theme_image)
    val imageDarkTheme = painterResource(id = R.drawable.keyboard_theme_image)
    val imageLightTheme = painterResource(id = R.drawable.keyboard_theme_image)

    var isSelected: Theme = currentTheme

    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            Theme(
                imageTheme = imageAutoTheme,
                isSelected = currentTheme is AutoTheme,
                modifier = Modifier.weight(1f)
            ) { }
            Theme(
                imageTheme = imageDarkTheme,
                isSelected = currentTheme is DarkTheme,
                modifier = Modifier.weight(1f)
            ) {}
            Theme(
                imageTheme = imageLightTheme,
                isSelected = currentTheme is LightTheme,
                modifier = Modifier.weight(1f)
            ) {}
        }
    }
}

@Composable
fun Theme(imageTheme: Painter, isSelected: Boolean, modifier: Modifier, onSelected: () -> Unit) {
    val borderWidth = if (isSelected) 3.dp else (-2).dp
    val themeStyle =
        modifier
            .padding(10.dp)
            .border(width = borderWidth, color = colors.secondary, shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp))
            .background(colors.secondaryVariant)
            .padding(14.dp)
            .fillMaxHeight()
            .clickable { onSelected() }

    Column(
        modifier = themeStyle,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = imageTheme, contentDescription = null)
        Spacer(modifier = Modifier.height(15.dp))
    }
}