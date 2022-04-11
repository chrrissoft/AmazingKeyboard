package com.chrrissoft.amazingkeyboard

import android.content.Context
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import com.chrrissoft.amazingkeyboard.composables.KeyboardScreen

class AndroidKeyboardView(context: Context) : FrameLayout(context) {

    constructor(service: IMEService) : this(service as Context) {
        inflate(service, R.layout.keyboard_view, this)
        findViewById<ComposeView>(R.id.compose_view).setContent {
            KeyboardScreen(connection = service)
        }
    }
}

