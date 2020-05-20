package com.example.feature_movieslist.domain

import com.example.core.domain.MovieEntity
import com.example.core.prefs.UserPrefs
import com.example.feature_movieslist.data.FavoritesRepository
import io.reactivex.Observable
import javax.inject.Inject

class FavoritesInteractor @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val userPrefs: UserPrefs
) {

    fun getFavorites(): Observable<List<MovieEntity>> {
        return favoritesRepository.getFavorites(userPrefs.userId)
            .map { movlieList -> movlieList.sortedBy { it.title } }
    }
}
