package com.chrrissoft.amazingkeyboard.uilayer.keyboard.core

import android.content.Context
import android.util.Log
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import com.chrrissoft.amazingkeyboard.R
import com.chrrissoft.amazingkeyboard.datalayer.services.IMEService
import com.google.android.gms.ads.*

class AndroidKeyboardView(context: Context) : FrameLayout(context) {

    constructor(service: IMEService) : this(service as Context) {
        inflate(service, R.layout.keyboard_view, this)

        val mAdView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        findViewById<ComposeView>(R.id.compose_view).setContent {
            Column() {
                KeyboardScreen(connection = service)
            }
        }
    }
}
