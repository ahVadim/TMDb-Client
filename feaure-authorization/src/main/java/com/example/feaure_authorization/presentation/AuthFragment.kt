package com.example.feaure_authorization.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.changedText
import com.example.core.util.observe
import com.example.core.util.viewBindings
import com.example.core.util.viewModelFromProvider
import com.example.feaure_authorization.R
import com.example.feaure_authorization.databinding.FragmentAuthorizationBinding
import com.example.feaure_authorization.di.DaggerAuthComponent
import javax.inject.Inject
import javax.inject.Provider

class AuthFragment : BaseFragment(R.layout.fragment_authorization) {

    @Inject
    internal lateinit var viewModelProvider: Provider<AuthViewModel>
    private val authViewModel: AuthViewModel by viewModelFromProvider { viewModelProvider }

    private val binding by viewBindings(FragmentAuthorizationBinding::bind)

    override fun onAttach(context: Context) {
        DaggerAuthComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(authViewModel.liveState, ::renderState)
        observe(authViewModel.eventsQueue, ::onEvent)

        binding.authLoginEditText.doAfterTextChanged {
            authViewModel.onLoginChange(it.toString())
        }
        binding.authPasswordEditText.doAfterTextChanged {
            authViewModel.onPasswordChange(it.toString())
        }

        binding.authLoginButton.setOnClickListener {
            val login = binding.authLoginEditText.text?.toString()
            val password = binding.authPasswordEditText.text?.toString()
            if (login != null && password != null) {
                authViewModel.onLoginButtonClick(login, password)
            }
        }
    }

    private fun renderState(state: AuthViewState) {
        binding.authLoginEditText.changedText = state.login
        binding.authPasswordEditText.changedText = state.password
        setErrorState(state.errorState)
        binding.authLoginButton.isEnabled = state.isLoginButtonEnabled
    }

    private fun setErrorState(errorState: AuthErrorState) {
        when (errorState) {
            AuthErrorState.TryLater -> {
                binding.authErrorText.setText(R.string.error_try_later)
                binding.authErrorText.isVisible = true
            }
            AuthErrorState.IncorrectData -> {
                binding.authErrorText.setText(R.string.error_incorrect_auth_data)
                binding.authErrorText.isVisible = true
            }
            AuthErrorState.None -> {
                binding.authErrorText.text = null
                binding.authErrorText.isVisible = false
            }
        }
    }
}
