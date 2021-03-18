package com.example.feature_pincode.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.presentation.Event
import com.example.core.util.observe
import com.example.core.util.viewBindings
import com.example.core.util.viewModelFromProvider
import com.example.feature_pincode.R
import com.example.feature_pincode.databinding.FragmentPincodeBinding
import com.example.feature_pincode.di.DaggerPincodeComponent
import com.example.feature_pincode.presentation.events.OpenBiometrics
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject
import javax.inject.Provider

class PincodeFragment: BaseFragment(R.layout.fragment_pincode) {

    @Inject
    internal lateinit var viewModelProvider: Provider<PincodeViewModel>
    private val pincodeViewModel: PincodeViewModel by viewModelFromProvider { viewModelProvider }

    private val binding by viewBindings(FragmentPincodeBinding::bind)

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onAttach(context: Context) {
        DaggerPincodeComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnItemClickListener { item, _ ->
            pincodeViewModel.onItemClick(item)
        }
        binding.pincodeButtons.adapter = adapter
        binding.backButton.setOnClickListener { pincodeViewModel.onBackClick() }
        observe(pincodeViewModel.liveState, ::renderState)
        observe(pincodeViewModel.eventsQueue, ::onEvent)
    }

    private fun renderState(state: PincodeViewState) {
        adapter.update(state.pincodeItems)

        when (state.screenState) {
            ScreenState.NewPinCode -> binding.profileName.setText(R.string.new_pincode_title)
            is ScreenState.RepeatPinCode -> binding.profileName.setText(R.string.repeat_pincode_title)
            is ScreenState.AuthPinCode -> binding.profileName.text = state.screenState.userName
        }
        binding.backButton.isVisible = state.screenState.isBackVisible
        binding.profileName.isVisible = !binding.profileName.text.isNullOrBlank()
        binding.profileAvatar.isVisible = state.screenState is ScreenState.AuthPinCode

        binding.pincodeError.isInvisible = !state.isPincodeErrorVisible

        if (state.isPincodeErrorVisible) {
            binding.pincodeBubbles.setErrorState()
        } else {
            binding.pincodeBubbles.setActiveBubblesCount(state.currentPincode.length)
        }
    }

    override fun onEvent(event: Event) {
        super.onEvent(event)
        when (event) {
            is OpenBiometrics -> showBiometricDialog()
        }
    }

    private fun showBiometricDialog() {
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(
            this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    pincodeViewModel.onBiometricsAuthenticationFailed()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    pincodeViewModel.onBiometricsAuthenticationSucceed()
                }

                override fun onAuthenticationFailed() {
                    pincodeViewModel.onBiometricsAuthenticationFailed()
                }
            })
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometrics_promp_title))
            .setNegativeButtonText(getString(R.string.cancel))
            .build()
        biometricPrompt.authenticate(promptInfo)
    }
}
