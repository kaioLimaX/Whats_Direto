package com.example.utils

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.whatsappdireto.model.Countries
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.io.IOException

object JsonUtil {

    fun getPaisesFromAsset(context: Context, fileName: String): List<Countries>? {
        val jsonString: String
        jsonString = context.assets.open( fileName) . bufferedReader( ) . use { it.readText() }
        return if (jsonString != null) {
            val listPaisType = object : TypeToken<List<Countries>>() {}.type
            Gson().fromJson(jsonString, listPaisType)
        } else {
            null
        }
    }

}