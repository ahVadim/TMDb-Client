package com.example.feature_movieslist.presentation.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.CoreComponentHolder
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseFragment
import com.example.core.presentation.statedelegate.ListStateDelegate
import com.example.core.util.observe
import com.example.feature_movieslist.databinding.FragmentMovieslistBinding
import com.example.feature_movieslist.di.DaggerMoviesListComponent
import com.example.feature_movieslist.presentation.MovieItemGrid
import com.example.feature_movieslist.presentation.MovieItemLine
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject

class MoviesListFragment : BaseFragment() {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val moviesListViewModel: MoviesListViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentMovieslistBinding? = null
    private val binding get() = _binding!!

    private var isGridLayout = false

    private val stateDelegate by lazy {
        ListStateDelegate(
            contentView = binding.moviesRecycler,
            emptyStateView = binding.moviesEmpty,
            loadingView = binding.moviesProgress,
            shouldHideContentWhenLoading = false,
            showData = ::renderMoviesList
        )
    }

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onAttach(context: Context) {
        DaggerMoviesListComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
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
        binding.moviesRecycler.adapter = adapter
        binding.moviesRecycler.layoutManager = LinearLayoutManager(context)

        binding.searchInputText.doAfterTextChanged {
            moviesListViewModel.onSearchInputTextChange(it?.toString())
        }

        binding.moviesGridSwitch.setOnClickListener {
            moviesListViewModel.onSwitchGridClick()
        }

        observe(moviesListViewModel.liveState, ::renderState)
        observe(moviesListViewModel.eventsQueue, ::onEvent)
    }

    private fun renderState(state: MoviesListViewState) {
        if (state.isGridLayout != this.isGridLayout) {
            binding.moviesRecycler.layoutManager = if (state.isGridLayout) {
                GridLayoutManager(context, GRID_SPAN_COUNT)
            } else {
                LinearLayoutManager(context)
            }
            this.isGridLayout = state.isGridLayout
        }
        stateDelegate.renderState(state.listState)
    }

    private fun renderMoviesList(movies: List<MovieEntity>) {
        adapter.update(movies.map {
            if (isGridLayout) {
                MovieItemGrid(it, moviesListViewModel::onMovieClick)
            } else {
                MovieItemLine(it, moviesListViewModel::onMovieClick)
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
