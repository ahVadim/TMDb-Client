package com.example.feature_pincode.presentation.items

import android.view.View
import com.example.feature_pincode.R
import com.example.feature_pincode.databinding.ItemPincodeNumberBinding
import com.xwray.groupie.viewbinding.BindableItem

class NumberItem(private val number: Int) : BindableItem<ItemPincodeNumberBinding>() {

    override fun getLayout() = R.layout.item_pincode_number

    override fun initializeViewBinding(view: View): ItemPincodeNumberBinding {
        return ItemPincodeNumberBinding.bind(view)
    }

    override fun bind(viewBinding: ItemPincodeNumberBinding, position: Int) {
        viewBinding.number.text = number.toString()
    }
}
