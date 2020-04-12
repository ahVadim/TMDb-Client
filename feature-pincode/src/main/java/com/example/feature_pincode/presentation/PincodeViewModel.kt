package com.example.feature_pincode.presentation

import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.BaseViewModel
import com.example.feature_pincode.presentation.items.ExitItem
import com.example.feature_pincode.presentation.items.FingerprintItem
import com.example.feature_pincode.presentation.items.NumberItem
import com.xwray.groupie.Item
import javax.inject.Inject

class PincodeViewModel @Inject constructor() : BaseViewModel() {

    var liveState = MutableLiveData<PincodeViewState>(createInitialState())

    private fun createInitialState(): PincodeViewState {
        val items = mutableListOf<Item<*>>()
        items.addAll((1..9).map { NumberItem(it) })
        items.add(ExitItem())
        items.add(NumberItem(0))
        items.add(FingerprintItem())
        return PincodeViewState(
            isPincodeErrorVisible = false,
            pincodeItems = items
        )
    }
}
