package com.example.feature_pincode.presentation.items

import android.view.View
import androidx.core.view.isVisible
import com.example.feature_pincode.R
import com.example.feature_pincode.databinding.ItemPincodeFingerprintBinding
import com.xwray.groupie.viewbinding.BindableItem

class FingerprintItem(
    private val isAvailable: Boolean
) : BindableItem<ItemPincodeFingerprintBinding>() {

    override fun getLayout() = R.layout.item_pincode_fingerprint

    override fun initializeViewBinding(view: View): ItemPincodeFingerprintBinding {
        return ItemPincodeFingerprintBinding.bind(view)
    }

    override fun bind(viewBinding: ItemPincodeFingerprintBinding, position: Int) {
        viewBinding.root.isVisible = isAvailable
    }
}
