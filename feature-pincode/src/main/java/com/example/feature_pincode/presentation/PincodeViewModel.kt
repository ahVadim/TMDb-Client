package com.example.feature_pincode.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.core.prefs.UserPrefs
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.events.Exit
import com.example.core.presentation.events.ShowSnackbarResId
import com.example.core.util.delegate
import com.example.feature_pincode.R
import com.example.feature_pincode.presentation.events.OpenBiometrics
import com.example.feature_pincode.presentation.items.DeleteItem
import com.example.feature_pincode.presentation.items.ExitItem
import com.example.feature_pincode.presentation.items.FingerprintItem
import com.example.feature_pincode.presentation.items.NumberItem
import com.xwray.groupie.Item
import javax.inject.Inject

class PincodeViewModel @Inject constructor(
    private val userPrefs: UserPrefs,
    context: Context
) : BaseViewModel() {

    val liveState = MutableLiveData<PincodeViewState>(createInitialState())
    var state by liveState.delegate()

    private val pincodeSize = context.resources.getInteger(R.integer.pincode_size)

    private fun createInitialState(): PincodeViewState {
        val screenState = if (userPrefs.userPincode?.length != pincodeSize) {
            ScreenState.NewPinCode
        } else {
            ScreenState.AuthPinCode(userPrefs.userName ?: userPrefs.userLogin)
        }

        val items = mutableListOf<Item<*>>()
        items.addAll((1..9).map { NumberItem(it) })
        items.add(if (screenState is ScreenState.AuthPinCode) FingerprintItem() else ExitItem())
        items.add(NumberItem(0))
        items.add(DeleteItem())

        return PincodeViewState(
            screenState = screenState,
            currentPincode = "",
            isPincodeErrorVisible = false,
            pincodeItems = items
        )
    }

    fun onItemClick(item: Item<*>) {
        when (item) {
            is NumberItem -> processNewNumber(item.number)
            is DeleteItem -> state = state.copy(
                currentPincode = state.currentPincode.dropLast(1),
                isPincodeErrorVisible = false
            )
            is FingerprintItem -> eventsQueue.offer(OpenBiometrics)
            is ExitItem -> eventsQueue.offer(Exit)
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

        if (state.currentPincode.length == pincodeSize) {
            when (val screenState = state.screenState) {

                ScreenState.NewPinCode -> processNewPincode(
                    pincode = state.currentPincode
                )

                is ScreenState.RepeatPinCode -> processRepeatPincode(
                    pincode = state.currentPincode,
                    previousPincode = screenState.previousPincode
                )

                is ScreenState.AuthPinCode -> processAuthPincode(
                    pincode = state.currentPincode
                )
            }
        }
    }

    private fun processNewPincode(pincode: String) {
        state = state.copy(screenState = ScreenState.RepeatPinCode(pincode), currentPincode = "")
    }

    private fun processRepeatPincode(pincode: String, previousPincode: String) {
        if (pincode == previousPincode) {
            userPrefs.userPincode = pincode
            navigateTo(PincodeFragmentDirections.actionPincodeToMainScreen())
        } else {
            state = state.copy(isPincodeErrorVisible = true)
        }
    }

    private fun processAuthPincode(pincode: String) {
        if (pincode == userPrefs.userPincode) {
            navigateTo(PincodeFragmentDirections.actionPincodeToMainScreen())
        } else {
            state = state.copy(isPincodeErrorVisible = true)
        }
    }

    fun onBiometricsAuthenticationFailed() {
        eventsQueue.offer(ShowSnackbarResId(R.string.biometrics_failed_error_text))
    }

    fun onBiometricsAuthenticationSucceed() {
        navigateTo(PincodeFragmentDirections.actionPincodeToMainScreen())
    }
}
