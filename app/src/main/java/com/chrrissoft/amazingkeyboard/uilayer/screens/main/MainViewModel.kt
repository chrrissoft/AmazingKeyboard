package com.chrrissoft.amazingkeyboard.uilayer.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.chrrissoft.amazingkeyboard.datalayer.datastore.darkMode.DarkModeKeyboardColorsSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val settings: DarkModeKeyboardColorsSettings) : ViewModel() {

    val storedKeyboardColorSettings = settings.darkModeKeyboardColorsSettingsFlow.asLiveData()
    fun changeTheme() {
        viewModelScope.launch {
//            settings.changeTheme()
        }
    }
}