package com.example.feature_pincode.presentation.items

import android.view.View
import com.example.feature_pincode.R
import com.example.feature_pincode.databinding.ItemPincodeExitBinding
import com.xwray.groupie.viewbinding.BindableItem

class ExitItem : BindableItem<ItemPincodeExitBinding>() {

    override fun getLayout() = R.layout.item_pincode_exit

    override fun initializeViewBinding(view: View): ItemPincodeExitBinding {
        return ItemPincodeExitBinding.bind(view)
    }

    override fun bind(viewBinding: ItemPincodeExitBinding, position: Int) {
    }
}
