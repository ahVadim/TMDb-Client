package com.example.feature_profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.feature_profile.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val profileViewModel by viewModels<ProfileViewModel>()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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
        binding.profileLogoutButton.setOnClickListener { profileViewModel.onLogoutButtonClick() }
    }

    private fun renderState(state: ProfileViewState) {
        binding.profileName.text = state.userName
        binding.profileMail.text = state.userMail
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
