package com.example.feature_movieslist.presentation.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.statedelegate.ListViewState
import com.example.core.util.delegate
import com.example.feature_movieslist.domain.FavoritesInteractor
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class FavoritesListViewModel @Inject constructor(
    favoritesInteractor: FavoritesInteractor,
) : BaseViewModel() {

    val liveState = MutableLiveData(createInitialData())
    private var state by liveState.delegate()

    private fun createInitialData() = FavoritesListViewState(
        listState = ListViewState.Loading(),
        isGridLayout = false
    )

    init {
        viewModelScope.launch {
            favoritesInteractor.getFavorites()
                .catch { error ->
                    Timber.e(error)
                    state = state.copy(listState = ListViewState.Data(emptyList()))
                }
                .collect {
                    state = state.copy(listState = ListViewState.Data(it))
                }
        }
    }

    fun onSearchInputTextChange(text: String?) {
        // todo implement searching
    }

    fun onMovieClick(movie: MovieEntity) {
        navigateTo(
            FavoritesListFragmentDirections
                .actionFavoritesListFragmentToMovieDetailsFragment(movie)
        )
    }

    fun onSwitchGridClick() {
        state = state.copy(isGridLayout = !state.isGridLayout)
    }
}
