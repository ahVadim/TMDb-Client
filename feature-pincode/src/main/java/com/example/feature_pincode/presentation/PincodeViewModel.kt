package com.example.feature_pincode.presentation

import androidx.lifecycle.MutableLiveData
import com.example.core.prefs.UserPrefs
import com.example.core.presentation.BaseViewModel
import com.example.core.util.delegate
import com.example.feature_pincode.presentation.items.ExitItem
import com.example.feature_pincode.presentation.items.FingerprintItem
import com.example.feature_pincode.presentation.items.NumberItem
import com.xwray.groupie.Item
import javax.inject.Inject

class PincodeViewModel @Inject constructor(
    private val userPrefs: UserPrefs
) : BaseViewModel() {

    val liveState = MutableLiveData<PincodeViewState>(createInitialState())
    var state by liveState.delegate()

    private fun createInitialState(): PincodeViewState {
        val items = mutableListOf<Item<*>>()
        items.addAll((1..9).map { NumberItem(it) })
        items.add(ExitItem())
        items.add(NumberItem(0))
        items.add(FingerprintItem())
        return PincodeViewState(
            currentPincode = "",
            isPincodeErrorVisible = false,
            pincodeItems = items
        )
    }

    fun onItemClick(item: Item<*>) {
        when (item) {
            is NumberItem -> processNewNumber(item.number)
        }
    }

    private fun processNewNumber(number: Int) {
        state = if (state.isPincodeErrorVisible) {
            state.copy(
                isPincodeErrorVisible = false,
                currentPincode = number.toString()
            )
        } else {
            state.copy(currentPincode = state.currentPincode + number)
        }

        if (state.currentPincode == userPrefs.userPincode) {
            // navigate next
        }
    }
}
