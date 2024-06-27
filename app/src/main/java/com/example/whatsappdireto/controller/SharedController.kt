package com.example.whatsappdireto.controller

import com.example.whatsappdireto.sharedPreferences.PreferencesManager

class SharedController(private val preferencesManager: PreferencesManager) {

    fun initializeApp() {
        if (preferencesManager.isFirstRun()) {
            preferencesManager.setDefaultNumber(1)
            preferencesManager.setFirstRun(false)
        }
    }

    fun getDefaultNumber(): Int {
        return preferencesManager.getDefaultNumber()
    }
}