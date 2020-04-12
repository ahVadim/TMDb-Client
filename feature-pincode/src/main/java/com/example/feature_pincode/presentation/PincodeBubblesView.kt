package com.example.feature_pincode.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.feature_pincode.R

class PincodeBubblesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_BUBBLES_COUNT = 4
    }

    private val bubblesCount: Int

    init {
        orientation = HORIZONTAL
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PincodeBubblesView)
            bubblesCount = typedArray.getInteger(
                R.styleable.PincodeBubblesView_bubbles_count,
                DEFAULT_BUBBLES_COUNT
            )
            typedArray.recycle()
        } else {
            bubblesCount = DEFAULT_BUBBLES_COUNT
        }

        dividerDrawable = ContextCompat.getDrawable(context, R.drawable.pincode_bubble_divider)
        showDividers = SHOW_DIVIDER_MIDDLE

        val bubbleSize = resources.getDimensionPixelSize(R.dimen.pincode_bubble_size)

        (0 until bubblesCount).forEach { _ ->
            val imageView = ImageView(context).apply {
                layoutParams = ViewGroup.LayoutParams(bubbleSize, bubbleSize)
                setImageResource(R.drawable.pincode_bubble_bg)
            }
            this.addView(imageView)
        }
    }
}
