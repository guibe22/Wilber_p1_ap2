package com.example.wilber_p1_ap2.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val PREFERENCE_NAME="CounterStore"
private const val COUNTER_KEY="CounterKey"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

