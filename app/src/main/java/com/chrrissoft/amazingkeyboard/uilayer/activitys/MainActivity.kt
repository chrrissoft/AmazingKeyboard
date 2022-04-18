package com.chrrissoft.amazingkeyboard.uilayer.activitys

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.chrrissoft.amazingkeyboard.R
import com.chrrissoft.amazingkeyboard.uilayer.theme.AmazingKeyboardTheme
import com.google.android.gms.ads.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazingKeyboardApp() {
                Options() {
                val test =
                    ShowInputPicker()
                test.showPicker(this)
            }

                val adWidth: Int = LocalConfiguration.current.screenWidthDp - 32
                val requestLimit = 10
                var requestIntent = 0

                Column(modifier = Modifier.background(MaterialTheme.colors.primary)) {
                    AndroidView(
                        factory = { context ->
                            AdView(context).apply {
                                adSize = AdSize.BANNER
                                adUnitId = context.getString(R.string.ad_id_adaptive_banner)
                                loadAd(AdRequest.Builder().build())
                                adListener = object : AdListener() {
                                    override fun onAdFailedToLoad(p0: LoadAdError) {
                                        super.onAdFailedToLoad(p0)
                                        if (requestIntent < requestLimit) {
                                            onAdLoaded()
                                        }
                                        Log.d("Activity", requestIntent.toString())
                                        requestIntent++
                                    }
                                }
                            }
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun Options(showPicker: () -> Unit) {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val ctx = LocalContext.current
        val (text, setValue) = remember { mutableStateOf(TextFieldValue("Escribe aqui")) }
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp), onClick = {
            ctx.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }) {
            Text(text = "Permitir como metodo de entrada")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(modifier = Modifier.fillMaxWidth().padding(15.dp), onClick = { showPicker() }) {
            Text(text = "Seleccionar como metodo de entrada")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = text, onValueChange = setValue, modifier = Modifier.fillMaxWidth())
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


