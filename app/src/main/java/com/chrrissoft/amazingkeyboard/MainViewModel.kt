package com.chrrissoft.amazingkeyboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.chrrissoft.amazingkeyboard.datastore.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val settings: Settings) : ViewModel() {

    val storedKeyboardColorSettings = settings.settingsKeyboardColorSettingsFlow.asLiveData()
    fun changeTheme() {
        viewModelScope.launch {
            settings.changeTheme()
        }
    }
}