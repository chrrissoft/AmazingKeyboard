package com.chrrissoft.amazingkeyboard.datastore

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.chrrissoft.amazingkeyboard.AmazingKeyboard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.function.DoublePredicate
import javax.inject.Inject
import javax.inject.Singleton

private const val SETTINGS_PREFERENCE_NAME = "Settings"

@Singleton
class Settings @Inject constructor(private val context: AmazingKeyboard) {
    private val Context.settingsStore: DataStore<Preferences> by preferencesDataStore(
        name = SETTINGS_PREFERENCE_NAME)

    // get data from dataStore
    val settingsKeyboardColorSettingsFlow: Flow<KeyboardColorsSetting> = context.settingsStore.data
        .catch {  if ( it is IOException ) emit(emptyPreferences()) else throw it }
        .map { preferences ->

            val isDarkTheme = preferences[DARK_THEME_KEY] ?: false

            if(!isDarkTheme) {
                val defaultBackgroundColor = DefaultKeyboardLightColors.BACKGROUND
                val defaultAssentKeyColor = DefaultKeyboardLightColors.ASSENT
                val defaultLetterColor = DefaultKeyboardLightColors.LETTER
                val defaultKeyColor = DefaultKeyboardLightColors.KEY

                val backgroundColor = preferences[KEYBOARD_BACKGROUND_COLOR_KEY] ?: defaultBackgroundColor
                val assentKeyColor = preferences[KEYBOARD_ASSENT_COLOR_KEY] ?: defaultAssentKeyColor
                val letterColor = preferences[KEYBOARD_LETTER_COLOR_KEY] ?: defaultLetterColor
                val keyColor = preferences[KEYBOARD_KEY_COLOR_KEY] ?: defaultKeyColor

                KeyboardLightColors(backgroundColor, assentKeyColor, letterColor, keyColor)

            } else {

                val defaultBackgroundColor = DefaultKeyboardDarkColors.BACKGROUND
                val defaultAssentKeyColor = DefaultKeyboardDarkColors.ASSENT
                val defaultLetterColor = DefaultKeyboardDarkColors.LETTER
                val defaultKeyColor = DefaultKeyboardDarkColors.KEY

                val backgroundColor = preferences[KEYBOARD_BACKGROUND_COLOR_KEY] ?: defaultBackgroundColor
                val assentKeyColor = preferences[KEYBOARD_ASSENT_COLOR_KEY] ?: defaultAssentKeyColor
                val letterColor = preferences[KEYBOARD_LETTER_COLOR_KEY] ?: defaultLetterColor
                val keyColor = preferences[KEYBOARD_KEY_COLOR_KEY] ?: defaultKeyColor

                KeyboardDarkColors(backgroundColor, assentKeyColor, letterColor, keyColor)
            }

        }


    suspend fun changeKeyboardColor(preferencesColorKey: Preferences.Key<Int>, newColor: Int) {
        context.settingsStore.edit { preferences ->
            preferences[preferencesColorKey] = newColor
        }
    }

    suspend fun changeTheme() {
        context.settingsStore.edit { preferences ->
            val theme = preferences[DARK_THEME_KEY] ?: false
            preferences[DARK_THEME_KEY] = !theme
        }
    }

    companion object {
        val DARK_THEME_KEY = booleanPreferencesKey(name = "DarkTheme")
        val KEYBOARD_KEY_COLOR_KEY = intPreferencesKey(name = "keyboardKeyColor")
        val KEYBOARD_ASSENT_COLOR_KEY = intPreferencesKey(name = "keyboardAssentColor")
        val KEYBOARD_LETTER_COLOR_KEY = intPreferencesKey(name = "keyboardLetterColor")
        val KEYBOARD_BACKGROUND_COLOR_KEY = intPreferencesKey(name = "keyboardBackgroundColor")
    }
}

abstract class KeyboardColorsSetting
abstract class KeyboardStyleSetting

data class KeyboardLightColors(
    val backgroundColor: Comparable<*>,
    val assentKeyColor: Comparable<*>,
    val letterColor: Comparable<*>,
    val keyColor: Comparable<*>,
) : KeyboardColorsSetting()
data class KeyboardDarkColors(
    val backgroundColor: Comparable<*>,
    val assentKeyColor: Comparable<*>,
    val letterColor: Comparable<*>,
    val keyColor: Comparable<*>,
) : KeyboardColorsSetting()

enum class DefaultKeyboardLightColors(color: Long) {
    KEY(color = 0xFFF5F5F5),
    ASSENT(color = 0xFFED4164),
    BACKGROUND(color = 0xFFE8EAEA),
    LETTER(color = 0xFF333333),
}
enum class DefaultKeyboardDarkColors(color: Int) {
    KEY(color = 12),
    ASSENT(color = 12),
    BACKGROUND(color = 12),
    LETTER(color = 12),
}


data class KeyboardDarkStyle(
    val backgroundColor: Comparable<*>,
    val assentKeyColor: Comparable<*>,
    val letterColor: Comparable<*>,
    val keyColor: Comparable<*>,
) : KeyboardStyleSetting()
data class KeyboardLightStyle(
    val backgroundColor: Comparable<*>,
    val assentKeyColor: Comparable<*>,
    val letterColor: Comparable<*>,
    val keyColor: Comparable<*>,
) : KeyboardStyleSetting()

enum class DefaultKeyboardLightStyle(value: Int) {
    KEY_SIZE(12),
    KEY_ROUND(12),
    LETTER_SIZE(12),
    LETTER_WEIGHT(12),
}
enum class DefaultKeyboardDarkStyle(color: Int) {
    KEY(color = 12),
    ASSENT(color = 12),
    BACKGROUND(color = 12),
    LETTER(color = 12),
}
