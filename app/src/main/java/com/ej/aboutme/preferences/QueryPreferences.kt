package com.ej.aboutme.preferences

import android.content.Context

private const val PREF_LOGIN_CHK = "loginCheck"
private const val PREF_LAST_RESULT_ID = "lastResultId"
private const val PREF_IS_POLLING = "isPolling"

class QueryPreferences {
    fun getLoginCheck(context: Context) : String?{
        val pref =context.getSharedPreferences(PREF_LOGIN_CHK,Context.MODE_PRIVATE)
        val email = pref.getString("email","none")
        return email
    }
}