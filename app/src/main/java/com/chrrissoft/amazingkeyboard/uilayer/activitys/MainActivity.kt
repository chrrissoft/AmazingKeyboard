package com.chrrissoft.amazingkeyboard.uilayer.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.chrrissoft.amazingkeyboard.uilayer.screens.main.MainScreen
import com.chrrissoft.amazingkeyboard.uilayer.theme.AmazingKeyboardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazingKeyboardApp {
                MainScreen()
            }
        }
    }
}

@Composable
fun AmazingKeyboardApp(content: @Composable () -> Unit) {
    AmazingKeyboardTheme() {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}


