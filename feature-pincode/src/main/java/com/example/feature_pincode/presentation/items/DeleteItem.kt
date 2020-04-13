package com.example.feature_pincode.presentation.items

import android.view.View
import com.example.feature_pincode.R
import com.example.feature_pincode.databinding.ItemPincodeDeleteBinding
import com.xwray.groupie.viewbinding.BindableItem

class DeleteItem : BindableItem<ItemPincodeDeleteBinding>() {

    override fun getLayout() = R.layout.item_pincode_delete

    override fun initializeViewBinding(view: View): ItemPincodeDeleteBinding {
        return ItemPincodeDeleteBinding.bind(view)
    }

    override fun bind(viewBinding: ItemPincodeDeleteBinding, position: Int) {
    }
}
