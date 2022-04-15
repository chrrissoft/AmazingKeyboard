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

const val PREFERENCE_DATASTORE_NAME = "Dark Style Settings"

@Singleton
class DarkStyleSettings @Inject constructor(private val context: AmazingKeyboardApp) {
    private val Context.lightModeStyleSettings: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCE_DATASTORE_NAME
    )

    val darkStyleSettingsFlow: Flow<KeyboardDarkStyle> =
        context.lightModeStyleSettings.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { preferences ->

                val defaultKeySize = DefaultKeyboardDarkModeStyle.KEY_SIZE
                val defaultKeyRound = DefaultKeyboardDarkModeStyle.KEY_ROUND
                val defaultLetterSize = DefaultKeyboardDarkModeStyle.LETTER__SIZE
                val defaultLetterWeight = DefaultKeyboardDarkModeStyle.LETTER_WEIGHT
                val defaultKeyboardHeight = DefaultKeyboardDarkModeStyle.KEYBOARD_HEIGHT

                val keySize = preferences[KEY_SIZE] ?: defaultKeySize
                val keyRound = preferences[KEY_ROUND] ?: defaultKeyRound
                val letterSize = preferences[LETTER_SIZE] ?: defaultLetterSize
                val letterWeight = preferences[LETTER_WEIGHT] ?: defaultLetterWeight
                val keyboardHeight = preferences[KEYBOARD_HEIGHT] ?: defaultKeyboardHeight

                KeyboardDarkStyle(keySize, keyRound, letterSize, letterWeight, keyboardHeight)
            }

    suspend fun changeStyle(valueKey: Preferences.Key<Int>, newValue: Int) {
        context.lightModeStyleSettings.edit { preferences ->
            preferences[valueKey] = newValue
        }
    }

    companion object {
        val KEY_SIZE = intPreferencesKey("keySize")
        val KEY_ROUND = intPreferencesKey("keyRound")
        val LETTER_SIZE = intPreferencesKey("letterSize")
        val LETTER_WEIGHT = intPreferencesKey("keyWeight")
        val KEYBOARD_HEIGHT = intPreferencesKey("keyboardHeight")
    }
}

data class KeyboardDarkStyle(
    val keySize: Comparable<*>,
    val keyRound: Comparable<*>,
    val letterSize: Comparable<*>,
    val letterWeight: Comparable<*>,
    val keyboardHeight: Comparable<*>
)

enum class DefaultKeyboardDarkModeStyle(
    val value: Int,
) {
    KEY_SIZE(12),
    KEY_ROUND(12),
    LETTER__SIZE(12),
    LETTER_WEIGHT(12),
    KEYBOARD_HEIGHT(204)
}