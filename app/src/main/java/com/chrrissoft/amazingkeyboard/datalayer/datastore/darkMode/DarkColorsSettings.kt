package com.chrrissoft.amazingkeyboard.datalayer.datastore.darkMode

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.chrrissoft.amazingkeyboard.AmazingKeyboardApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val SETTINGS_PREFERENCE_NAME = "Dark Mode Keyboard Colors Settings"

@Singleton
class DarkColorsSettings @Inject constructor(private val context: AmazingKeyboardApp) {
    private val Context.darkModeColorsSettings: DataStore<Preferences> by preferencesDataStore(
        name = SETTINGS_PREFERENCE_NAME
    )

    val darkColorsSettingsFlow: Flow<KeyboardDarkColors> =
        context.darkModeColorsSettings.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { preferences ->

                val defaultBackground = 0XFFF5F5F5
                val defaultAssentKey = 0XFFF5F5F5
                val defaultLetter = 0XFFF5F5F5
                val defaultKey = 0XFFF5F5F5

                val background =
                    preferences[KEYBOARD_BACKGROUND_DARK_COLOR_KEY] ?: defaultBackground
                val assentKey =
                    preferences[KEYBOARD_ASSENT_DARK_COLOR_KEY] ?: defaultAssentKey
                val letter = preferences[KEYBOARD_LETTER_DARK_COLOR_KEY] ?: defaultLetter
                val key = preferences[KEYBOARD_KEY__DARK_COLOR_KEY] ?: defaultKey

                KeyboardDarkColors(background, assentKey, letter, key)
            }


    suspend fun changeColor(preferencesColorKey: Preferences.Key<Int>, newColor: Int) {
        context.darkModeColorsSettings.edit { preferences ->
            preferences[preferencesColorKey] = newColor
        }
    }

    companion object {
        val KEYBOARD_KEY__DARK_COLOR_KEY = intPreferencesKey(name = "keyboardKeyColor")
        val KEYBOARD_ASSENT_DARK_COLOR_KEY = intPreferencesKey(name = "keyboardAssentColor")
        val KEYBOARD_LETTER_DARK_COLOR_KEY = intPreferencesKey(name = "keyboardLetterColor")
        val KEYBOARD_BACKGROUND_DARK_COLOR_KEY = intPreferencesKey(name = "keyboardBackgroundColor")
    }
}

data class KeyboardDarkColors(
    val background: Number,
    val assentKey: Number,
    val letter: Number,
    val key: Number,
)

enum class DefaultKeyboardDarkColors(color: Long) {
    KEY(color = 0xFF091939),
    ASSENT(color = 0xFF142446),
    BACKGROUND(color = 0xFFED4164),
    LETTER(color = 0xFF12B17E),
}
