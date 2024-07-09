package com.example.feature_movieslist.presentation.movies

import androidx.lifecycle.viewModelScope
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.statedelegate.ListViewState
import com.example.core.util.runCatchingCancellable
import com.example.feature_movieslist.data.MoviesSearchRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val moviesSearchRepository: MoviesSearchRepository,
) : BaseViewModel<MoviesListViewState>(
    initialState = MoviesListViewState(
        listState = ListViewState.Data(emptyList()),
        isGridLayout = false
    )
) {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY_MS = 300L
    }

    private var searchJob: Job? = null

    fun onSearchInputTextChange(text: String?) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            state = state.copy(listState = ListViewState.Loading())
            delay(timeMillis = SEARCH_DEBOUNCE_DELAY_MS)
            runCatchingCancellable(
                action = {
                    if (text.isNullOrBlank()) {
                        emptyList()
                    } else {
                        moviesSearchRepository.searchMovie(text)
                    }
                },
                onSuccess = { movies ->
                    state = state.copy(listState = ListViewState.Data(movies))
                },
                onError = {
                    state = state.copy(listState = ListViewState.Data(emptyList()))
                }
            )
        }
    }

    fun onMovieClick(movie: MovieEntity) {
        navigateTo(
            MoviesListFragmentDirections
                .actionMoviesListFragmentToMovieDetailsFragment(movie)
        )
    }

    fun onSwitchGridClick() {
        state = state.copy(isGridLayout = !state.isGridLayout)
    }
}
