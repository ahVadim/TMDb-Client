package com.example.movieslistapp.util

import android.content.res.Resources

object ScreenUtils {

    val KEYBOARD_MIN_HEIGHT = dpToPx(200)

    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}
