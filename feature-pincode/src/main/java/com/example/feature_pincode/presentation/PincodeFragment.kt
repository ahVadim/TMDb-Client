package com.example.feature_pincode.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.feature_pincode.databinding.FragmentPincodeBinding
import com.example.feature_pincode.di.DaggerPincodeComponent
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject

class PincodeFragment: BaseFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val profileViewModel: PincodeViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentPincodeBinding? = null
    private val binding get() = _binding!!

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onAttach(context: Context) {
        DaggerPincodeComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPincodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnItemClickListener { item, _ ->
            profileViewModel.onItemClick(item)
        }
        binding.pincodeButtons.adapter = adapter
        observe(profileViewModel.liveState, ::renderState)
        observe(profileViewModel.eventsQueue, ::onEvent)
    }

    private fun renderState(state: PincodeViewState) {
        adapter.update(state.pincodeItems)

        binding.profileName.isVisible = !state.title.isNullOrBlank()
        binding.profileName.text = state.title

        binding.pincodeError.isInvisible = !state.isPincodeErrorVisible

        if (state.isPincodeErrorVisible) {
            binding.pincodeBubbles.setErrorState()
        } else {
            binding.pincodeBubbles.setActiveBubblesCount(state.currentPincode.length)
        }
    }
}
