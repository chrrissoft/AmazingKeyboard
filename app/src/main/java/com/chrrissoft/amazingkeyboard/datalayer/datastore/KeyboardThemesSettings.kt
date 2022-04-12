package com.chrrissoft.amazingkeyboard.datalayer.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.chrrissoft.amazingkeyboard.datalayer.di.AmazingKeyboard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val SETTINGS_PREFERENCE_NAME = "Keyboard Themes Settings"

@Singleton
class KeyboardThemesSettings @Inject constructor(private val context: AmazingKeyboard) {

    private val Context.settingsStore: DataStore<Preferences> by preferencesDataStore(
        name = SETTINGS_PREFERENCE_NAME)

    val keyboardThemesSettingsFlow: Flow<Theme> = context.settingsStore.data
        .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
        .map { preferences ->

            when {
                preferences[AUTOMATICALLY_THEME_KEY] == true -> {
                    AutoTheme(true)
                }
                preferences[DARK_THEME_KEY] == true -> {
                    DarkTheme(true)
                }
                else -> {
                    LightTheme(true)
                }
            }
        }

    suspend fun setThemeAutomatically() {
        context.settingsStore.edit { preferences ->
            preferences[AUTOMATICALLY_THEME_KEY] = true
        }
    }

    suspend fun changeTheme() {
        context.settingsStore.edit { preferences ->
            preferences[AUTOMATICALLY_THEME_KEY] = false
            val darkTheme = preferences[DARK_THEME_KEY] ?: false
            preferences[DARK_THEME_KEY] = !darkTheme
        }
    }

    companion object {
        val AUTOMATICALLY_THEME_KEY = booleanPreferencesKey(name = "automaticallyTheme")
        val DARK_THEME_KEY = booleanPreferencesKey(name = "DarkTheme")
    }
}

abstract class Theme

data class DarkTheme(val enable: Boolean) : Theme()
data class LightTheme(val enable: Boolean) : Theme()
data class AutoTheme(val enable: Boolean) : Theme()