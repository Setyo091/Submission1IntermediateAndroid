package com.example.submission1intermediateandroid.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.submission1intermediateandroid.UserRepository
import com.example.submission1intermediateandroid.database.StoryDataBase
import com.example.submission1intermediateandroid.pref.UserPreference
import com.example.submission1intermediateandroid.pref.dataStore
import com.example.submission1intermediateandroid.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val dataBase = StoryDataBase.getDatabase(context)
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(dataBase, apiService, pref)
    }
}