package com.example.core.util

import android.widget.TextView

var TextView.changedText: String?
    get() = text.toString()
    set(value) {
        if (text.toString() != value) {
            text = value
        }
    }
