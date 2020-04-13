package com.example.feature_pincode.presentation

import com.xwray.groupie.Item

data class PincodeViewState(
    val currentPincode: String,
    val isPincodeErrorVisible: Boolean,
    val pincodeItems: List<Item<*>>
)
