package com.example.feaure_authorization.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.feaure_authorization.R
import com.example.feaure_authorization.databinding.FragmentAuthorizationBinding
import com.example.feaure_authorization.di.DaggerAuthComponent
import com.example.feaure_authorization.presentation.presenter.AuthPresenter
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class AuthFragment : BaseFragment(), AuthView {

    companion object {
        fun newInstance() = AuthFragment()
    }

    @Inject
    lateinit var presenterProvider: Provider<AuthPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

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

        binding.authLoginEditText.doAfterTextChanged {
            presenter.onUserDataChange(
                login = binding.authLoginEditText.text?.toString(),
                password = binding.authPasswordEditText.text?.toString()
            )
        }
        binding.authPasswordEditText.doAfterTextChanged {
            presenter.onUserDataChange(
                login = binding.authLoginEditText.text?.toString(),
                password = binding.authPasswordEditText.text?.toString()
            )
        }

        binding.authLoginButton.setOnClickListener {
            val login = binding.authLoginEditText.text?.toString()
            val password = binding.authPasswordEditText.text?.toString()
            if (login != null && password != null) {
                presenter.onLoginButtonClick(login, password)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setLoginButtonEnable(isEnable: Boolean) {
        binding.authLoginButton.isEnabled = isEnable
    }

    override fun setErrorState(errorState: AuthErrorState) {
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

    override fun showSuccessAuthorizationMessage() {
        Toast.makeText(context, R.string.success_authorization, Toast.LENGTH_LONG).show()
    }
}
