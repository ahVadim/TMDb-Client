package com.example.feature_profile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.core.util.viewBindings
import com.example.core.util.viewModelFromProvider
import com.example.feature_profile.R
import com.example.feature_profile.databinding.FragmentProfileBinding
import com.example.feature_profile.di.DaggerProfileComponent
import javax.inject.Inject
import javax.inject.Provider

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    @Inject
    internal lateinit var viewModelProvider: Provider<ProfileViewModel>
    private val profileViewModel: ProfileViewModel by viewModelFromProvider { viewModelProvider }

    private val binding by viewBindings(FragmentProfileBinding::bind)

    override fun onAttach(context: Context) {
        DaggerProfileComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
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
}
