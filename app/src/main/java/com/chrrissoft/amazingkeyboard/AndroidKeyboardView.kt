package com.chrrissoft.amazingkeyboard

import android.content.Context
import android.widget.Button
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import com.chrrissoft.amazingkeyboard.composables.KeyboardScreen
import com.chrrissoft.amazingkeyboard.util.unicodeToString

class AndroidKeyboardView(context: Context) : FrameLayout(context) {

    init {
        inflate(context, R.layout.keyboard_view, this)
        findViewById<ComposeView>(R.id.compose_view).setContent {
            KeyboardScreen()
        }
    }
}
