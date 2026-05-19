package com.swastik.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val USER_NAME = stringPreferencesKey("user_name")
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_PHONE = stringPreferencesKey("user_phone")
        private val USER_ROLE = stringPreferencesKey("user_role") // "buyer" or "seller"
        private val IS_SELLER_REGISTERED = booleanPreferencesKey("is_seller_registered")
        private val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
        private val USER_LOCATION = stringPreferencesKey("user_location")
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[IS_LOGGED_IN] ?: false
    }

    val userName: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[USER_NAME] ?: ""
    }

    val userEmail: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[USER_EMAIL] ?: ""
    }

    val userPhone: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[USER_PHONE] ?: ""
    }

    val userRole: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[USER_ROLE] ?: ""
    }

    val isSellerRegistered: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[IS_SELLER_REGISTERED] ?: false
    }

    val isDarkTheme: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[IS_DARK_THEME] ?: true
    }

    val userLocation: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[USER_LOCATION] ?: "Sector 14, Gurgaon"
    }

    suspend fun saveLogin(
        name: String,
        email: String,
        phone: String,
        role: String
    ) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = true
            prefs[USER_NAME] = name
            prefs[USER_EMAIL] = email
            prefs[USER_PHONE] = phone
            prefs[USER_ROLE] = role
        }
    }

    suspend fun updateRole(role: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_ROLE] = role
        }
    }

    suspend fun setSellerRegistered(isRegistered: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_SELLER_REGISTERED] = isRegistered
        }
    }

    suspend fun setDarkTheme(isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_DARK_THEME] = isDark
        }
    }

    suspend fun clearLogin() {
        context.dataStore.edit { it.clear() }
    }

    suspend fun setLocation(location: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_LOCATION] = location
        }
    }
}
