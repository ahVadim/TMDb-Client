package com.example.feature_movieslist.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.presentation.BaseFragment
import com.example.feature_movieslist.databinding.FragmentMovieslistBinding
import com.example.feature_movieslist.presentation.list.MoviesAdapter

class MoviesListFragment : BaseFragment() {

    private var _binding: FragmentMovieslistBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieslistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MoviesAdapter()
        binding.moviesRecycler.adapter = adapter
        binding.moviesRecycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
