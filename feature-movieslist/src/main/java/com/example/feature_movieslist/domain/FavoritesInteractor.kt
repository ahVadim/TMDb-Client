package com.example.feature_movieslist.domain

import com.example.core.domain.MovieEntity
import com.example.core.prefs.UserPrefs
import com.example.feature_movieslist.data.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesInteractor @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val userPrefs: UserPrefs
) {

    suspend fun getFavorites(): Flow<List<MovieEntity>> {
        return favoritesRepository.getFavorites(userPrefs.userId)
            .map { movlieList -> movlieList.sortedBy { it.title } }
    }
}
