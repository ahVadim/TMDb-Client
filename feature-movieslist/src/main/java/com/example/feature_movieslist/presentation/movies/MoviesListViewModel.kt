package com.example.feature_movieslist.presentation.movies

import androidx.lifecycle.MutableLiveData
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.statedelegate.ListViewState
import com.example.core.rxjava.SchedulersProvider
import com.example.core.util.delegate
import com.example.core.util.ioToMain
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

    val liveState = MutableLiveData<MoviesListViewState>(createInitialData())
    private var state by liveState.delegate()

    private fun createInitialData() = MoviesListViewState(
        listState = ListViewState.Data(emptyList()),
        isGridLayout = false
    )

    private var searchDisposable = Disposables.disposed()

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
            .map { ListViewState.Data(it) as ListViewState<MovieEntity> }
            .startWith(ListViewState.Loading())
            .subscribe({ listState ->
                           state = state.copy(listState = listState)
                       }, { error ->
                           Timber.e(error)
                           state = state.copy(listState = ListViewState.Data(emptyList()))
                       })
            .also { addDisposable(it) }
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
