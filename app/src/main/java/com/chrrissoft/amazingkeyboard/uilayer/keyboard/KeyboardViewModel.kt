//package com.chrrissoft.amazingkeyboard.uilayer.keyboard
//
//import androidx.datastore.preferences.core.Preferences
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.asLiveData
//import androidx.lifecycle.viewModelScope
//import com.chrrissoft.amazingkeyboard.datalayer.datastore.KeyboardThemesSettings
//import com.chrrissoft.amazingkeyboard.datalayer.datastore.darkMode.DarkColorsSettings
//import com.chrrissoft.amazingkeyboard.datalayer.datastore.darkMode.DarkStyleSettings
//import com.chrrissoft.amazingkeyboard.datalayer.datastore.lightMode.LightColorsSettings
//import com.chrrissoft.amazingkeyboard.datalayer.datastore.lightMode.LightMeasurementsSettings
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class KeyboardViewModel @Inject constructor(
//    private val themeSettings: KeyboardThemesSettings,
//    private val darkColorsSettings: DarkColorsSettings,
//    private val darkStyleSettings: DarkStyleSettings,
//    private val lightColorsSettings: LightColorsSettings,
//    private val lightStyleSettings: LightMeasurementsSettings
//) : ViewModel() {
//
//    val settingsTheme = themeSettings.keyboardThemesSettingsFlow.asLiveData()
//
//    // settings dark theme
//    val settingsDarkColors = darkColorsSettings.darkColorsSettingsFlow.asLiveData()
//    val settingsDarkStyle = darkStyleSettings.darkStyleSettingsFlow.asLiveData()
//
//    // settings light theme
//    val settingsLightColors = lightColorsSettings.lightColorsSettingsFlow.asLiveData()
//    val settingsLightStyle = lightStyleSettings.lightStyleSettingsFlow.asLiveData()
//
//    fun setAutoTheme() {
//        viewModelScope.launch { themeSettings.setAutoTheme() }
//    }
//
//    fun toggleTheme() {
//        viewModelScope.launch { themeSettings.changeTheme() }
//    }
//
//    fun setDarkColor(colorKey: Preferences.Key<Int>, newColor: Int) {
//        viewModelScope.launch { darkColorsSettings.changeColor(colorKey, newColor) }
//    }
//
//    fun setDarkStyle(styleKey: Preferences.Key<Int>, newStyle: Int) {
//        viewModelScope.launch { darkStyleSettings.changeStyle(styleKey, newStyle) }
//    }
//
//    fun setLightColor(colorKey: Preferences.Key<Long>, newColor: Long) {
//        viewModelScope.launch { lightColorsSettings.changeColor(colorKey, newColor) }
//    }
//
//    fun setLightStyle(styleKey: Preferences.Key<Int>, newStyle: Int) {
//        viewModelScope.launch { lightStyleSettings.changeStyle(styleKey, newStyle) }
//    }
//
//}