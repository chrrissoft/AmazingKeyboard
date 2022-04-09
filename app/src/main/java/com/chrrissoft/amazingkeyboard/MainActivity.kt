package com.chrrissoft.amazingkeyboard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.chrrissoft.amazingkeyboard.ui.theme.AmazingKeyboardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var darkTheme = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content(darkTheme = darkTheme) { changeTheme() } }
        observePreferences()
    }

    private fun changeTheme() {
        viewModel.changeTheme()
    }

    private fun observePreferences() {
        viewModel.storedKeyboardColorSettings.observe(this) {
            darkTheme = it
        }
    }
}

@Composable
fun Content(darkTheme: Boolean, changeTheme: () -> Unit) {
    AmazingKeyboardTheme(darkTheme = darkTheme) {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                Options(changeTheme = changeTheme)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun Options(changeTheme: () -> Unit) {
    Column(
        Modifier
            .padding(16.dp)
    ) {
        val (text, setValue) = remember { mutableStateOf(TextFieldValue("Try here")) }
        TextField(value = text, onValueChange = setValue, modifier = Modifier.fillMaxWidth())
        Button(onClick = changeTheme) {
            Text(text = "Change theme")
        }
    }
}
