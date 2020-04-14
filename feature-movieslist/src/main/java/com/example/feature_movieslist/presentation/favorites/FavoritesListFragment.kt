package com.example.feature_movieslist.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.presentation.BaseFragment
import com.example.feature_movieslist.databinding.FragmentFavoriteslistBinding

class FavoritesListFragment : BaseFragment() {

    private var _binding: FragmentFavoriteslistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteslistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
