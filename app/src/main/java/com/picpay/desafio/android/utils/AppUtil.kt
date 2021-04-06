package com.picpay.desafio.android.utils

import android.content.Context
import android.content.SharedPreferences

object AppUtil {

    fun salvarIdUsuario(context: Context, uiid: String?) {
        val preferences: SharedPreferences =
            context.getSharedPreferences("USER", Context.MODE_PRIVATE)
        preferences.edit().putString("UIID", uiid).apply()
    }
}
