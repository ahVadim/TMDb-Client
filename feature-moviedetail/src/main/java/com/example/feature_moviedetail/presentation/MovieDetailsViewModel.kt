package com.example.feature_moviedetail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.core.data.account.AccountRepository
import com.example.core.data.movies.MoviesRepository
import com.example.core.domain.MovieEntity
import com.example.core.presentation.AssistedViewModelFactory
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.events.PopBackStack
import com.example.core.rxjava.SchedulersProvider
import com.example.core.util.delegate
import com.example.core.util.delegateArgument
import com.example.core.util.ioToMain
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

@AssistedFactory
interface MoviesDetailsAssistedFactory : AssistedViewModelFactory<MovieDetailsViewModel>

class MovieDetailsViewModel @AssistedInject constructor(
    @Assisted handle: SavedStateHandle,
    moviesRepository: MoviesRepository,
    private val accountRepository: AccountRepository,
    private val schedulersProvider: SchedulersProvider
) : BaseViewModel() {

    private val movie: MovieEntity by handle.delegateArgument("movie_arg")

    val liveState = MutableLiveData(MovieDetailsViewState(movie = movie, isFavorite = false))
    private var state by liveState.delegate()

    init {
        moviesRepository.isMovieFavorite(movie.id)
            .ioToMain(schedulersProvider)
            .subscribeBy(
                onSuccess = { state = state.copy(isFavorite = it) },
                onError = Timber::e
            )
            .let(this::addDisposable)
    }

    fun onAddFavoriteButtonClick() {
        val newIsFavorite = !state.isFavorite
        state = state.copy(isFavorite = newIsFavorite)
        accountRepository.setMovieIsFavorite(
            movieId = state.movie.id,
            isFavorite = newIsFavorite
        )
            .ioToMain(schedulersProvider)
            .subscribeBy(
                onError = { error ->
                    Timber.e(error)
                    state = state.copy(isFavorite = !newIsFavorite)
                }
            )
            .let(this::addDisposable)
    }

    fun onBackClick() {
        eventsQueue.offer(PopBackStack)
    }
}
