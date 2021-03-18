package com.example.core.util

import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T : Any> SavedStateHandle.delegateArgument(
    argumentKey: String
) = object : ReadOnlyProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return requireNotNull(this@delegateArgument.get(argumentKey)) { "argument with key $argumentKey is empty" }
    }
}

inline fun <reified T> SavedStateHandle.delegateProperty(
    initialValue: T
) = object : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val key = property.name
        return this@delegateProperty.get(key) ?: initialValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val key = property.name
        this@delegateProperty[key] = value
    }
}
