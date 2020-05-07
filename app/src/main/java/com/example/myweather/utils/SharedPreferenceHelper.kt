package com.example.myweather.utils

import android.content.Context

import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit

class SharedPreferenceHelper(private val context: Context) {

    companion object {
        const val PREFERENCE_FILE_NAME = "PreferenceDataFile"
    }

    fun setString(key: String, value: String) {
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        sharedPreferences.edit(commit = true) { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String): String? {
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue)
    }

    fun setInt(key: String, value: Int) {
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun remove(key: String) {
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        sharedPreferences.edit(commit = true) { remove(key) }
    }


    fun contains(key: String): Boolean {
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        return sharedPreferences.contains(key)
    }//contains()


    fun deleteAll(){
        val appContext = context.applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }



}
