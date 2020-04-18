package com.example.core.prefs.delegates

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class IntPreference(
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
