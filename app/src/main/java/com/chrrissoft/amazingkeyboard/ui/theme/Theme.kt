package com.chrrissoft.amazingkeyboard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Colors
val White200 = Color(0xFFF5F5F5)
val White500 = Color(0xFFE8EAEA)

// Dark Colors
val DarkBlue200 = Color(0xFF091939)
val DarkBlue500 = Color(0xFF142446)

// General colors
val Red = Color(0xFFED4164)
val Green = Color(0xFF12B17E)

private val DarkColorPalette = darkColors(
    primary = DarkBlue200,
    primaryVariant = DarkBlue500,
    secondary = Green,
    secondaryVariant = Red,
    onPrimary = White200,
    onSecondary = White200
)

private val LightColorPalette = lightColors(
    primary = White200,
    primaryVariant = White500,
    secondary = Green,
    secondaryVariant = Red,
    onPrimary = DarkBlue200,
    onSecondary = White200
)

@Composable
fun AmazingKeyboardTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}