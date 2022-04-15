package com.chrrissoft.amazingkeyboard.uilayer.screens.main

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.chrrissoft.amazingkeyboard.datalayer.datastore.KeyboardThemesSettings
import com.chrrissoft.amazingkeyboard.datalayer.datastore.lightMode.LightColorsSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settings: KeyboardThemesSettings,
    private val colorsTest: LightColorsSettings
    ) : ViewModel() {

    val testColors = colorsTest.lightColorsSettingsFlow.asLiveData()

    fun changeTest(preferencesColorKey: Preferences.Key<Long>, newColor: Long) {

        viewModelScope.launch { colorsTest.changeColor(preferencesColorKey, newColor) }

    }


    val storedKeyboardThemeSettings = settings.keyboardThemesSettingsFlow.asLiveData()

    fun changeTheme() {
        viewModelScope.launch {
            settings.changeTheme()
        }
    }
    fun setAutoTheme() {
        viewModelScope.launch {
            settings.setAutoTheme()
        }
    }
}