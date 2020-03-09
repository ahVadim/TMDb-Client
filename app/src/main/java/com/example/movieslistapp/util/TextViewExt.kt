package com.example.movieslistapp.util

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.showIfNotBlank(text: String?) {
    if (text.isNullOrBlank()) {
        this.isVisible = false
    } else {
        this.text = text
        this.isVisible = true
    }
}
