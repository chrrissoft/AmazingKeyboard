package com.chrrissoft.amazingkeyboard.datalayer.datastore.lightMode

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

const val PREFERENCE_DATASTORE_NAME = "Light Style Settings"

@Singleton
class LightMeasurementsSettings @Inject constructor(private val context: AmazingKeyboardApp) {
    private val Context.lightModeStylesSettings: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCE_DATASTORE_NAME
    )

    val lightStyleSettingsFlow: Flow<KeyboardLightStyle> =
        context.lightModeStylesSettings.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { preferences ->

                val defaultKeySize = DefaultKeyboardLightStyle.KEY_SIZE
                val defaultKeyRound = DefaultKeyboardLightStyle.KEY_ROUND
                val defaultLetterSize = DefaultKeyboardLightStyle.LETTER__SIZE
                val defaultLetterWeight = DefaultKeyboardLightStyle.LETTER_WEIGHT

                val keySize = preferences[KEY_SIZE] ?: defaultKeySize
                val keyRound = preferences[KEY_ROUND] ?: defaultKeyRound
                val letterSize = preferences[LETTER_SIZE] ?: defaultLetterSize
                val letterWeight = preferences[LETTER_WEIGHT] ?: defaultLetterWeight

                KeyboardLightStyle(keySize, keyRound, letterSize, letterWeight)
            }

    suspend fun changeStyle(valueKey: Preferences.Key<Int>, newValue: Int) {
        context.lightModeStylesSettings.edit { preferences ->
            preferences[valueKey] = newValue
        }
    }

    companion object {
        val KEY_SIZE = intPreferencesKey("keySize")
        val KEY_ROUND = intPreferencesKey("keyRound")
        val LETTER_SIZE = intPreferencesKey("letterSize")
        val LETTER_WEIGHT = intPreferencesKey("keyWeight")
    }
}

data class KeyboardLightStyle(
    val keySize: Comparable<*>,
    val keyRound: Comparable<*>,
    val letterSize: Comparable<*>,
    val letterWeight: Comparable<*>,
)

enum class DefaultKeyboardLightStyle(
    val value: Int,
) {
    KEY_SIZE(12),
    KEY_ROUND(12),
    LETTER__SIZE(12),
    LETTER_WEIGHT(12)
}