package com.chrrissoft.amazingkeyboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.ui.theme.AmazingKeyboardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazingKeyboardTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Options()
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun Options() {
    Column(
        Modifier
            .padding(16.dp)
    ) {
        val (text, setValue) = remember { mutableStateOf(TextFieldValue("Try here")) }
        TextField(value = text, onValueChange = setValue, modifier = Modifier.fillMaxWidth())
    }
}
