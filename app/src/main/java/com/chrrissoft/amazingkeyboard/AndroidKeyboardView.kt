package com.chrrissoft.amazingkeyboard

import android.content.Context
import android.widget.FrameLayout
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.chrrissoft.amazingkeyboard.composables.KeyboardScreen

class AndroidKeyboardView(context: Context) : FrameLayout(context) {

    init {
        inflate(context, R.layout.keyboard_view, this)
        findViewById<ComposeView>(R.id.compose_view).setContent {
            KeyboardScreen()
        }
    }
}
