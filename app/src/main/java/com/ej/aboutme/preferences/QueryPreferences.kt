package com.ej.aboutme.preferences

import android.content.Context

private const val PREF_LOGIN_CHK = "loginCheck"


class QueryPreferences {
    fun getLoginCheck(context: Context) : String?{
        val pref =context.getSharedPreferences(PREF_LOGIN_CHK,Context.MODE_PRIVATE)
        val email = pref.getString("email","none")
        return email
    }
    fun setAutoLogin(context: Context,memberId : Long,email:String){
        val pref =context.getSharedPreferences(PREF_LOGIN_CHK,Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.apply {
            putLong("memberId",memberId)
            putString("email",email)
            apply()
        }
    }
}