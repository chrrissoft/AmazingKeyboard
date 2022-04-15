package com.chrrissoft.amazingkeyboard.datalayer.datastore.lightMode

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.chrrissoft.amazingkeyboard.AmazingKeyboardApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val SETTINGS_PREFERENCE_NAME = "Light Mode Keyboard Colors Settings"

@Singleton
class LightColorsSettings @Inject constructor(private val context: AmazingKeyboardApp) {
    private val Context.lightModeColorsSettings: DataStore<Preferences> by preferencesDataStore(
        name = SETTINGS_PREFERENCE_NAME
    )

    val lightColorsSettingsFlow: Flow<LightColors> =
        context.lightModeColorsSettings.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { preferences ->

                val defaultBackground = 0XFFF5F5F5
                val defaultAssentKey = 0XFFF5F5F5
                val defaultLetter = 0XFFF5F5F5
                val defaultKey = 0XFFF5F5F5

                val background =
                    preferences[KEYBOARD_BACKGROUND_LIGHT_COLOR_KEY] ?: defaultBackground
                val assentKey =
                    preferences[KEYBOARD_ASSENT_LIGHT_COLOR_KEY] ?: defaultAssentKey
                val letter = preferences[KEYBOARD_LETTER_LIGHT_COLOR_KEY] ?: defaultLetter
                val key = preferences[KEYBOARD_KEY__LIGHT_COLOR_KEY] ?: defaultKey

                LightColors(background, assentKey, letter, key)

            }

    suspend fun changeColor(preferencesColorKey: Preferences.Key<Long>, newColor: Long) {
        context.lightModeColorsSettings.edit { preferences ->
            preferences[preferencesColorKey] = newColor
        }
    }

    companion object {
        val KEYBOARD_KEY__LIGHT_COLOR_KEY = longPreferencesKey(name = "keyboardKeyLightColor")
        val KEYBOARD_ASSENT_LIGHT_COLOR_KEY = longPreferencesKey(name = "keyboardAssentLightColor")
        val KEYBOARD_LETTER_LIGHT_COLOR_KEY = longPreferencesKey(name = "keyboardLetterLightColor")
        val KEYBOARD_BACKGROUND_LIGHT_COLOR_KEY = longPreferencesKey(name = "keyboardBackgroundLightColor")
    }

}

data class LightColors(
    val background: Long,
    val assentKey: Long,
    val letter: Long,
    val key: Long,
)