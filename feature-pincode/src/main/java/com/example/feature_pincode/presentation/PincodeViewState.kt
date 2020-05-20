package com.example.feature_pincode.presentation

import com.xwray.groupie.Item

data class PincodeViewState(
    val screenState: ScreenState,
    val currentPincode: String,
    val isPincodeErrorVisible: Boolean,
    val pincodeItems: List<Item<*>>
)

sealed class ScreenState(val isBackVisible: Boolean) {

    object NewPinCode : ScreenState(isBackVisible = true)

    class RepeatPinCode(val previousPincode: String) : ScreenState(isBackVisible = true)

    class AuthPinCode(val userName: String?) : ScreenState(isBackVisible = false)
}

