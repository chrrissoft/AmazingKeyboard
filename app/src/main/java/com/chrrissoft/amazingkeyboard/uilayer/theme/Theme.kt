package com.chrrissoft.amazingkeyboard.uilayer.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Light Colors
val White200 = Color(0xFFF7F7F7)
val White500 = Color(0xFFE8EAEA)

// Dark Colors
val DarkBlue200 = Color(0xFF091939)
val DarkBlue500 = Color(0xFF1B2D52)

// General colors
val Red = Color(0xFFED4164)
val Green = Color(0xFF12B17E)

private val DarkColorPalette = darkColors(
    primary = DarkBlue200,
    primaryVariant = DarkBlue500,
    secondary = Green,
    secondaryVariant = Red,
    onPrimary = White200,
    onSecondary = White200,
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
    darkTheme: Boolean = isSystemInDarkTheme(),
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


/*


@Immutable
data class KeyboardColors(
val key: Color,
val assent: Color,
val letter: Color,
val background: Color,
)

data class KeyboardMeasurements(
val keySize: Int,
val keyRound: Int,
val letterSize: Int,
val letterWeight: Int,
)

@Immutable
data class Typography(
val body: TextStyle,
val title: TextStyle
)

@Immutable
data class Elevation(
val default: Dp,
val pressed: Dp
)

val LocalColors = staticCompositionLocalOf {
KeyboardColors(
key = Color.Unspecified,
assent = Color.Unspecified,
letter = Color.Unspecified,
background = Color.Unspecified
)
}
val LocalTypography = staticCompositionLocalOf {
Typography(
body = TextStyle.Default,
title = TextStyle.Default
)
}
val LocalElevation = staticCompositionLocalOf {
Elevation(
default = Dp.Unspecified,
pressed = Dp.Unspecified
)
}

@Composable
fun KeyboardTheme(
darkTheme: Boolean,
autoTheme: Boolean,
content: @Composable () -> Unit
) {
val keyboardColors = KeyboardColors(
key = Color(0xFFDD0D3C),
letter = Color(0xFFC20029),
assent = Color(0xFFC20029),
background = Color(0xFFC20029)
)
val keyboardMeasurements = KeyboardMeasurements(
keySize = 12,
keyRound = 12,
letterSize = 12,
letterWeight = 12,
)
val typography = Typography(
body = TextStyle(fontSize = 16.sp),
title = TextStyle(fontSize = 32.sp)
)
val elevation = Elevation(
default = 4.dp,
pressed = 8.dp
)
CompositionLocalProvider(
LocalColors provides keyboardColors,
LocalTypography provides typography,
LocalElevation provides elevation,
content = content
)
}

// Use with eg. CustomTheme.elevation.small
object KeyboardTheme {
val colors: KeyboardColors
@Composable
get() = LocalColors.current
val typography: Typography
@Composable
get() = LocalTypography.current
val elevation: Elevation
@Composable
get() = LocalElevation.current
}


*/




