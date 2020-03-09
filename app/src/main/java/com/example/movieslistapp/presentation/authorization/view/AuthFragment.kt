package com.example.movieslistapp.presentation.authorization.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.movieslistapp.R
import com.example.movieslistapp.databinding.FragmentAuthorizationBinding
import com.example.movieslistapp.di.ComponentManager
import com.example.movieslistapp.presentation.authorization.presenter.AuthPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class AuthFragment : MvpAppCompatFragment(), AuthView {

    companion object {
        fun newInstance() = AuthFragment()
    }

    @Inject
    lateinit var presenterProvider: Provider<AuthPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private var binding: FragmentAuthorizationBinding? = null

    private val tryLaterText by lazy {
        requireContext().getString(R.string.error_try_later)
    }

    private val incorrectDataText by lazy {
        requireContext().getString(R.string.error_incorrect_auth_data)
    }

    private val successAuthorizationText by lazy {
        requireContext().getString(R.string.success_authorization)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentManager.getAuthComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.authLoginEditText?.doAfterTextChanged {
            presenter.onUserDataChange(
                login = binding?.authLoginEditText?.text?.toString(),
                password = binding?.authPasswordEditText?.text?.toString()
            )
        }
        binding?.authPasswordEditText?.doAfterTextChanged {
            presenter.onUserDataChange(
                login = binding?.authLoginEditText?.text?.toString(),
                password = binding?.authPasswordEditText?.text?.toString()
            )
        }

        binding?.authLoginButton?.setOnClickListener {
            presenter.onLoginButtonClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun setLoginButtonEnable(isEnable: Boolean) {
        binding?.authLoginButton?.isEnabled = isEnable
    }

    override fun showTryLaterError() {
        binding?.authErrorText?.text = tryLaterText
        binding?.authErrorText?.isVisible = true
    }

    override fun showIncorrectDataError() {
        binding?.authErrorText?.text = incorrectDataText
        binding?.authErrorText?.isVisible = true
    }

    override fun hideError() {
        binding?.authErrorText?.isVisible = false
    }

    override fun showSuccessAuthorizationMessage() {
        Toast.makeText(context, successAuthorizationText, Toast.LENGTH_LONG).show()
    }
}
