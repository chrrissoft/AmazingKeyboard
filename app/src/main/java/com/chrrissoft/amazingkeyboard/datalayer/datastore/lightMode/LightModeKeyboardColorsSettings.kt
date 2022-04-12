package com.chrrissoft.amazingkeyboard.datalayer.datastore.lightMode

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.chrrissoft.amazingkeyboard.datalayer.di.AmazingKeyboard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val SETTINGS_PREFERENCE_NAME = "Light Mode Keyboard Colors Settings"

@Singleton
class LightModeKeyboardColorsSettings @Inject constructor(private val context: AmazingKeyboard) {
    private val Context.lightModeColorsSettings: DataStore<Preferences> by preferencesDataStore(
        name = SETTINGS_PREFERENCE_NAME
    )

    val lightModeKeyboardColorsSettingsFlow: Flow<KeyboardLightColors> =
        context.lightModeColorsSettings.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { preferences ->

                val defaultBackground = DefaultKeyboardLightColors.BACKGROUND
                val defaultAssentKey = DefaultKeyboardLightColors.ASSENT
                val defaultLetter = DefaultKeyboardLightColors.LETTER
                val defaultKey = DefaultKeyboardLightColors.KEY

                val background =
                    preferences[KEYBOARD_BACKGROUND_LIGHT_COLOR_KEY] ?: defaultBackground
                val assentKey =
                    preferences[KEYBOARD_ASSENT_LIGHT_COLOR_KEY] ?: defaultAssentKey
                val letter = preferences[KEYBOARD_LETTER_LIGHT_COLOR_KEY] ?: defaultLetter
                val key = preferences[KEYBOARD_KEY__LIGHT_COLOR_KEY] ?: defaultKey

                KeyboardLightColors(background, assentKey, letter, key)

            }

    suspend fun changeKeyboardColor(preferencesColorKey: Preferences.Key<Int>, newColor: Int) {
        context.lightModeColorsSettings.edit { preferences ->
            preferences[preferencesColorKey] = newColor
        }
    }

    companion object {
        val KEYBOARD_KEY__LIGHT_COLOR_KEY = intPreferencesKey(name = "keyboardKeyLightColor")
        val KEYBOARD_ASSENT_LIGHT_COLOR_KEY = intPreferencesKey(name = "keyboardAssentLightColor")
        val KEYBOARD_LETTER_LIGHT_COLOR_KEY = intPreferencesKey(name = "keyboardLetterLightColor")
        val KEYBOARD_BACKGROUND_LIGHT_COLOR_KEY = intPreferencesKey(name = "keyboardBackgroundLightColor")
    }

}

data class KeyboardLightColors(
    val background: Comparable<*>,
    val assentKey: Comparable<*>,
    val letter: Comparable<*>,
    val key: Comparable<*>,
)

enum class DefaultKeyboardLightColors(color: Long) {
    KEY(color = 0xFFF5F5F5),
    ASSENT(color = 0xFFED4164),
    LETTER(color = 0xFF333333),
    BACKGROUND(color = 0xFFE8EAEA),
}