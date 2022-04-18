package com.chrrissoft.amazingkeyboard

import android.app.Application
import android.util.Log
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AmazingKeyboardApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        Log.d("initialize", "add inicializado")
    }
}