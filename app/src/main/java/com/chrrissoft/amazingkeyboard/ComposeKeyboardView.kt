package com.chrrissoft.amazingkeyboard

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import com.chrrissoft.amazingkeyboard.composables.KeyboardScreen


class ComposeKeyboardView constructor(
    context: Context,
    ) : AbstractComposeView(context) {

    @Composable
    override fun Content() {
        KeyboardScreen()
    }
}