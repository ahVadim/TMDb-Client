package com.example.feature_movieslist.presentation.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.badoo.mvicore.modelWatcher
import com.example.core.di.CoreComponentHolder
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseFragment
import com.example.core.presentation.statedelegate.ListStateDelegate
import com.example.core.util.observe
import com.example.core.util.viewBindings
import com.example.core.util.viewModelFromProvider
import com.example.feature_movieslist.R
import com.example.feature_movieslist.databinding.FragmentFavoriteslistBinding
import com.example.feature_movieslist.di.DaggerFavoritesListComponent
import com.example.feature_movieslist.presentation.MovieItemGrid
import com.example.feature_movieslist.presentation.MovieItemLine
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject
import javax.inject.Provider

class FavoritesListFragment : BaseFragment(R.layout.fragment_favoriteslist) {

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    @Inject
    internal lateinit var viewModelProvider: Provider<FavoritesListViewModel>
    private val moviesListViewModel: FavoritesListViewModel by viewModelFromProvider { viewModelProvider }

    private val binding by viewBindings(FragmentFavoriteslistBinding::bind)

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onAttach(context: Context) {
        DaggerFavoritesListComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesRecycler.adapter = adapter

        binding.searchInputText.doAfterTextChanged {
            moviesListViewModel.onSearchInputTextChange(it?.toString())
        }

        binding.moviesGridSwitch.setOnClickListener {
            moviesListViewModel.onSwitchGridClick()
        }

        observe(moviesListViewModel.liveState, stateWatcher::invoke)
        observe(moviesListViewModel.eventsQueue, ::onEvent)
    }

    private val stateWatcher = modelWatcher<FavoritesListViewState> {

        watch(FavoritesListViewState::isGridLayout) { isGrid ->
            binding.moviesRecycler.layoutManager = if (isGrid) {
                GridLayoutManager(context, GRID_SPAN_COUNT)
            } else {
                LinearLayoutManager(context)
            }
        }

        watch({ it }) { viewState ->
            getStateDelegate { movies -> renderMoviesList(movies, viewState.isGridLayout) }
                .renderState(viewState.listState)
        }
    }

    private fun getStateDelegate(showData: (List<MovieEntity>) -> Unit) = ListStateDelegate(
        contentView = binding.moviesRecycler,
        emptyStateView = binding.moviesEmpty,
        loadingView = binding.moviesProgress,
        shouldHideContentWhenLoading = false,
        showData = showData
    )

    private fun renderMoviesList(movies: List<MovieEntity>, isGrid: Boolean) {
        adapter.update(movies.map {
            if (isGrid) {
                MovieItemGrid(it, moviesListViewModel::onMovieClick)
            } else {
                MovieItemLine(it, moviesListViewModel::onMovieClick)
            }
        })
    }

    override fun onDestroyView() {
        stateWatcher.clear()
        super.onDestroyView()
    }
}
