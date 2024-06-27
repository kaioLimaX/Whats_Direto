package com.example.whatsappdireto.sharedPreferences

import android.content.Context
import com.example.whatsappdireto.sharedPreferences.ContantsPreference.PREFS_NAME

class PreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(ContantsPreference.KEY_IS_FIRST_RUN, true)
    }

    fun setFirstRun(isFirstRun: Boolean) {
        sharedPreferences.edit().putBoolean(ContantsPreference.KEY_IS_FIRST_RUN, isFirstRun).apply()
    }

    fun getDefaultNumber(): Int {
        return sharedPreferences.getInt(ContantsPreference.KEY_DEFAULT_NUMBER, 0)
    }

    fun setDefaultNumber(number: Int) {
        sharedPreferences.edit().putInt(ContantsPreference.KEY_DEFAULT_NUMBER, number).apply()
    }
}