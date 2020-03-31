package com.example.feaure_authorization.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.changedText
import com.example.core.util.observe
import com.example.feaure_authorization.R
import com.example.feaure_authorization.databinding.FragmentAuthorizationBinding
import com.example.feaure_authorization.di.DaggerAuthComponent
import com.example.feaure_authorization.presentation.presenter.AuthViewModel
import com.example.feaure_authorization.presentation.presenter.AuthViewState
import javax.inject.Inject

class AuthFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val authViewModel: AuthViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAuthComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(authViewModel.liveState, ::renderState)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    fun showSuccessAuthorizationMessage() {
        Toast.makeText(context, R.string.success_authorization, Toast.LENGTH_LONG).show()
    }
}
