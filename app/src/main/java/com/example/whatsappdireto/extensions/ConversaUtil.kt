package com.example.whatsappdireto.extensions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View

fun View.openWhatsAppChat(toNumber: String) {
    val url = "https://api.whatsapp.com/send?phone=+55$toNumber"
    try {
        context.packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
        context.startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
    } catch (e: PackageManager.NameNotFoundException) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))}}
