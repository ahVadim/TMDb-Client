package com.example.movieslistapp.presentation.authorization.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieslistapp.databinding.FragmentAuthorizationBinding
import com.example.movieslistapp.di.ComponentManager
import com.example.movieslistapp.presentation.authorization.presenter.AuthPresenter
import javax.inject.Inject

class AuthFragment: Fragment() {

    companion object {
        fun newInstance() = AuthFragment()
    }

    @Inject
    lateinit var presenter: AuthPresenter

    private var binding: FragmentAuthorizationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.getAuthComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
