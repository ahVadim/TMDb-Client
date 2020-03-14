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
import com.example.core.util.ScreenUtils
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

    private var binding: FragmentAuthorizationBinding? = null

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
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.root?.let { changeTopMarginDependOnKeyboardShown(it) }

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
            val login = binding?.authLoginEditText?.text?.toString()
            val password = binding?.authPasswordEditText?.text?.toString()
            if (login != null && password != null) {
                presenter.onLoginButtonClick(login, password)
            }
        }
    }

    private fun changeTopMarginDependOnKeyboardShown(root: View) {
        root.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = root.rootView.height - root.height
            (binding?.authTitle?.layoutParams as? ViewGroup.MarginLayoutParams)?.let { newParams ->
                newParams.topMargin = if (heightDiff > ScreenUtils.KEYBOARD_MIN_HEIGHT) {
                    resources.getDimensionPixelSize(R.dimen.margin_24)
                } else {
                    resources.getDimensionPixelSize(R.dimen.auth_title_top_margin)
                }
                binding?.authTitle?.layoutParams = newParams
            }
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
        binding?.authErrorText?.setText(R.string.error_try_later)
        binding?.authErrorText?.isVisible = true
    }

    override fun showIncorrectDataError() {
        binding?.authErrorText?.setText(R.string.error_incorrect_auth_data)
        binding?.authErrorText?.isVisible = true
    }

    override fun hideError() {
        binding?.authErrorText?.isVisible = false
    }

    override fun showSuccessAuthorizationMessage() {
        Toast.makeText(context, R.string.success_authorization, Toast.LENGTH_LONG).show()
    }
}
