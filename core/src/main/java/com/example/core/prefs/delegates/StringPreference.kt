package com.example.core.prefs.delegates

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPreference(
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
