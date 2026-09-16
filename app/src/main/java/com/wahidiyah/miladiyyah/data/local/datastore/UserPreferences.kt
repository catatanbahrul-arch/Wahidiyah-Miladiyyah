package com.wahidiyah.miladiyyah.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

object PreferenceKeys {
    val REGION_CODE = stringPreferencesKey("region_code")
    val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
}
