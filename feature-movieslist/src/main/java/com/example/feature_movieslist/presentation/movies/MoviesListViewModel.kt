package com.example.feature_movieslist.presentation.movies

import androidx.lifecycle.MutableLiveData
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseViewModel
import com.example.core.rxjava.SchedulersProvider
import com.example.core.util.ioToMain
import com.example.core.util.onNext
import com.example.feature_movieslist.data.MoviesSearchRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val moviesSearchRepository: MoviesSearchRepository,
    private val schedulersProvider: SchedulersProvider
) : BaseViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY_MS = 300L
    }

    var liveState = MutableLiveData<MoviesListViewState>(createInitialState())

    private var searchDisposable = Disposables.disposed()

    private fun createInitialState(): MoviesListViewState {
        return MoviesListViewState(
            emptyList()
        )
    }

    fun onSearchInputTextChange(text: String?) {
        searchDisposable.dispose()
        searchDisposable = Observable.timer(SEARCH_DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS)
            .flatMapSingle {
                if (text.isNullOrBlank()) {
                    Single.just(emptyList())
                } else {
                    moviesSearchRepository.searchMovie(text)
                }
            }
            .ioToMain(schedulersProvider)
            .subscribe({ movies ->
                           liveState.onNext(MoviesListViewState(movies))
                       }, { error ->
                           Timber.e(error)
                       })
            .also { addDisposable(it) }
    }

    fun onMovieClick(movie: MovieEntity) {
        navigateTo(
            MoviesListFragmentDirections
                .actionMoviesListFragmentToMovieDetailsFragment(movie)
        )
    }
}
