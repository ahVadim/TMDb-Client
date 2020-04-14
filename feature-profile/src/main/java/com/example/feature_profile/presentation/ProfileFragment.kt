package com.example.feature_profile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.feature_profile.databinding.FragmentProfileBinding
import com.example.feature_profile.di.DaggerProfileComponent
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val profileViewModel: ProfileViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerProfileComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(profileViewModel.liveState, ::renderState)
        observe(profileViewModel.eventsQueue, ::onEvent)
        binding.profileLogoutButton.setOnClickListener { profileViewModel.onLogoutButtonClick() }
    }

    private fun renderState(state: ProfileViewState) {
        binding.profileName.text = state.userName
        binding.profileMail.text = state.userMail
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
