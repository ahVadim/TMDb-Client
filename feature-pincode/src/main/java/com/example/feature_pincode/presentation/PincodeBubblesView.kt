package com.example.feature_pincode.presentation

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class PincodeBubblesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
    }
}
