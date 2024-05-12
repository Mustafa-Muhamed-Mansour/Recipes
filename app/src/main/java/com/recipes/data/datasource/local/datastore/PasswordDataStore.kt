package com.recipes.data.datasource.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.recipes.app.utils.UserSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PasswordDataStore(
    private val context: Context
) {

    companion object {
        val USER_PASSWORD = stringPreferencesKey("user_password")
    }

    // Read password the purpose a user
    val getPassword: Flow<String?> = context.PrefDataStore.data.map {
        it[USER_PASSWORD] ?: "null"
    }
}

// Preference DataStore
val Context.PrefDataStore: DataStore<Preferences> by preferencesDataStore("preference")
// Prototype DataStore
val Context.ProtoDataStore by dataStore("prototype.json", UserSerializer)