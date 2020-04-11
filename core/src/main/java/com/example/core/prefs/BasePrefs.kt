package com.example.core.prefs

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class BasePrefs(
    context: Context, fileName: String
) : SharedPreferences by context.getSharedPreferences(fileName, Context.MODE_PRIVATE) {

    protected class StringPreference(
        private val key: String,
        private val defValue: String?
    ) : ReadWriteProperty<SharedPreferences, String?> {

        override fun getValue(thisRef: SharedPreferences, property: KProperty<*>): String? {
            return thisRef.getString(key, defValue)
        }

        override fun setValue(thisRef: SharedPreferences, property: KProperty<*>, value: String?) {
            if (value == null) {
                thisRef.edit().remove(key).apply()
            } else {
                thisRef.edit().putString(key, value).apply()
            }
        }
    }

    protected class IntPreference(
        private val key: String,
        private val defValue: Int
    ) : ReadWriteProperty<SharedPreferences, Int> {

        override fun getValue(thisRef: SharedPreferences, property: KProperty<*>): Int {
            return thisRef.getInt(key, defValue)
        }

        override fun setValue(thisRef: SharedPreferences, property: KProperty<*>, value: Int) {
            thisRef.edit().putInt(key, value).apply()
        }
    }
}
