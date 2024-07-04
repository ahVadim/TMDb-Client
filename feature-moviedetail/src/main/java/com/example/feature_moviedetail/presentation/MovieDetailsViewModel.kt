package com.example.feature_moviedetail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.data.account.AccountRepository
import com.example.core.data.movies.MoviesRepository
import com.example.core.domain.MovieEntity
import com.example.core.presentation.AssistedViewModelFactory
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.events.PopBackStack
import com.example.core.util.delegate
import com.example.core.util.delegateArgument
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

@AssistedFactory
interface MoviesDetailsAssistedFactory : AssistedViewModelFactory<MovieDetailsViewModel>

class MovieDetailsViewModel @AssistedInject constructor(
    @Assisted handle: SavedStateHandle,
    moviesRepository: MoviesRepository,
    private val accountRepository: AccountRepository,
) : BaseViewModel() {

    private val movie: MovieEntity by handle.delegateArgument("movie_arg")

    val liveState = MutableLiveData(MovieDetailsViewState(movie = movie, isFavorite = false))
    private var state by liveState.delegate()

    private val defaultExceptionHandler =
        CoroutineExceptionHandler { _, throwable -> Timber.e(throwable) }

    init {
        viewModelScope.launch(defaultExceptionHandler) {
            val isFavorite = moviesRepository.isMovieFavorite(movie.id)
            state = state.copy(isFavorite = isFavorite)
        }
    }

    fun onAddFavoriteButtonClick() {
        val newIsFavorite = !state.isFavorite
        state = state.copy(isFavorite = newIsFavorite)
        viewModelScope.launch {
            runCatching {
                accountRepository.setMovieIsFavorite(
                    movieId = state.movie.id,
                    isFavorite = newIsFavorite
                )
            }.onFailure { error ->
                Timber.e(error)
                state = state.copy(isFavorite = !newIsFavorite)
            }
        }
    }

    fun onBackClick() {
        eventsQueue.offer(PopBackStack)
    }
}
