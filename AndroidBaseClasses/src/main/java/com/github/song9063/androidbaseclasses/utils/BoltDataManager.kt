package com.github.song9063.androidbaseclasses.utils

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

open class BoltDataManager(context: Context, prefName: String) {
    private val pref: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun getString(key: String, defaultValue: String): String {
        return pref.getString(key, defaultValue).toString()
    }

    fun setString(key: String, value: String){
        pref.edit().putString(key, value).apply()
    }

    fun setJSON(key: String, obj: JSONObject){
        val strJson = obj.toString()
        setString(key, strJson)
    }

    fun getJSON(key: String): JSONObject? {
        val strJson = getString(key,"")
        if(strJson.isNotEmpty()){
            return JSONObject(strJson)
        }
        return null
    }

    fun setSignedUserJSON(obj: JSONObject?){
        if(obj != null)
            setJSON("SIGNED_USER_JSON", obj)
        else
            setString("SIGNED_USER_JSON","")
    }
    fun getSignedUserJSON(): JSONObject?{
        return getJSON("SIGNED_USER_JSON")
    }
}