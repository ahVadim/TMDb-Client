package com.example.movieslistapp.presentation.authorization.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.movieslistapp.databinding.FragmentAuthorizationBinding
import com.example.movieslistapp.di.ComponentManager
import com.example.movieslistapp.presentation.authorization.presenter.AuthPresenter
import com.example.movieslistapp.util.showIfNotBlank
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun setLoginButtonEnable(isEnable: Boolean) {
        binding?.authLoginButton?.isEnabled = isEnable
    }

    override fun showError(error: String?) {
        binding?.authErrorText?.showIfNotBlank(error)
    }
}
