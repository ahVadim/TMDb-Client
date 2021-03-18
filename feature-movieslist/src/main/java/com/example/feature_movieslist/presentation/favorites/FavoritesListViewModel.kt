package com.example.feature_movieslist.presentation.favorites

import androidx.lifecycle.MutableLiveData
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.statedelegate.ListViewState
import com.example.core.rxjava.SchedulersProvider
import com.example.core.util.delegate
import com.example.core.util.ioToMain
import com.example.feature_movieslist.domain.FavoritesInteractor
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class FavoritesListViewModel @Inject constructor(
    favoritesInteractor: FavoritesInteractor,
    schedulersProvider: SchedulersProvider
) : BaseViewModel() {

    val liveState = MutableLiveData(createInitialData())
    private var state by liveState.delegate()

    private fun createInitialData() = FavoritesListViewState(
        listState = ListViewState.Loading(),
        isGridLayout = false
    )

    init {
        favoritesInteractor.getFavorites()
            .ioToMain(schedulersProvider)
            .subscribeBy(
                onNext = { state = state.copy(listState = ListViewState.Data(it)) },
                onError = { error ->
                    Timber.e(error)
                    state = state.copy(listState = ListViewState.Data(emptyList()))
                }
            )
            .let(this::addDisposable)
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
