package com.example.feature_profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.presentation.BaseFragment
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
}
