package com.chrrissoft.amazingkeyboard.uilayer.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chrrissoft.amazingkeyboard.datalayer.datastore.lightMode.LightColorsSettings.Companion.KEYBOARD_KEY__LIGHT_COLOR_KEY
import com.chrrissoft.amazingkeyboard.uilayer.screens.main.MainViewModel
import com.chrrissoft.amazingkeyboard.uilayer.theme.AmazingKeyboardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var darkTheme = false
    private var autoTheme = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazingKeyboardApp(true, false) {
                //                MainScreen()
//                Try()
            }
        }
    }
}

fun onChangeTest(viewModel: MainViewModel) {
    viewModel.changeTest(KEYBOARD_KEY__LIGHT_COLOR_KEY, 0XFF000000)
}

@Composable
fun Try(viewModel: MainViewModel = hiltViewModel()) {

    val testCurrentColor = viewModel.testColors.observeAsState()

    Box(modifier = Modifier
        .width(200.dp)
        .height(200.dp)
        .background(Color(color = testCurrentColor.value!!.assentKey))
        .clickable { onChangeTest(viewModel) })
}

@Composable
fun AmazingKeyboardApp(autoTheme: Boolean, darkTheme: Boolean, content: @Composable () -> Unit) {
    AmazingKeyboardTheme(autoTheme = autoTheme, darkTheme = darkTheme) {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}


