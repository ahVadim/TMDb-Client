package com.example.feature_pincode.presentation

import com.xwray.groupie.Item

data class PincodeViewState(
    val screenState: ScreenState,
    val currentPincode: String,
    val isPincodeErrorVisible: Boolean,
    val pincodeItems: List<Item<*>>
)

sealed class ScreenState {

    object NewPinCode : ScreenState()

    class RepeatPinCode(val previousPincode: String) : ScreenState()

    class AuthPinCode(val userName: String?) : ScreenState()
}

