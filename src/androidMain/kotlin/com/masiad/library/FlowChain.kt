package com.masiad.library

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "FlowChainDataStore")

actual class FlowChain(
    applicationContext: Context
) {

    private val securityHelper by lazy { SecurityHelper(applicationContext) }
    private val dataStore by lazy { applicationContext.dataStore }

    actual fun getValue(
        key: String,
        defaultValue: String?
    ): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]?.let { encrypted ->
                securityHelper.decrypt(encrypted)
            } ?: defaultValue
        }
    }

    actual suspend fun setValue(
        key: String,
        value: String
    ) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = securityHelper.encrypt(value)
        }
    }
}