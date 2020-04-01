package com.example.feature_movieslist.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.feature_movieslist.databinding.FragmentMovieslistBinding
import com.example.feature_movieslist.di.DaggerMoviesListComponent
import com.example.feature_movieslist.presentation.list.MoviesAdapter
import javax.inject.Inject

class MoviesListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val moviesListViewModel: MoviesListViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentMovieslistBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMoviesListComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onCreate(savedInstanceState)
    }

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
        adapter = MoviesAdapter(moviesListViewModel::onMovieClick)
        binding.moviesRecycler.adapter = adapter
        binding.moviesRecycler.layoutManager = LinearLayoutManager(context)

        binding.searchInputText.doAfterTextChanged {
            moviesListViewModel.onSearchInputTextChange(it?.toString())
        }

        observe(moviesListViewModel.liveState, ::renderState)
        observe(moviesListViewModel.eventsQueue, ::onEvent)
    }

    private fun renderState(state: MoviesListViewState) {
        adapter.submitList(state.moviesList)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
