package com.example.core.util

import android.content.Context
import androidx.annotation.StringRes

class ResourceUtil(private val context: Context) {

    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}