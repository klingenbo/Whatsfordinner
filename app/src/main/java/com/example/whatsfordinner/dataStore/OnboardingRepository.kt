package com.example.whatsfordinner.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.get

class OnboardingRepository(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val HAS_SEEN_ONBOARDING = booleanPreferencesKey("has_seen_onboarding")
    }

    val hasSeenOnboarding: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[HAS_SEEN_ONBOARDING] ?: false
        }

    suspend fun setHasSeenOnboarding() {
        dataStore.edit { preferences ->
            preferences[HAS_SEEN_ONBOARDING] = true
        }
    }

}