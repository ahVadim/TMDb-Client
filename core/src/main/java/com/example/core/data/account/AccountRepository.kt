package com.example.core.data.account

import com.example.core.data.account.dto.SetIsFavoriteRequestDto
import com.example.core.di.DispatcherIO
import com.example.core.domain.AccountInfo
import com.example.core.mapper.AccountMapper
import com.example.core.network.api.AccountApi
import com.example.core.prefs.UserPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountApi: AccountApi,
    private val accountMapper: AccountMapper,
    private val userPrefs: UserPrefs,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) {

    companion object {
        private const val MOVIE_MEDIA_TYPE = "movie"
    }

    suspend fun getAccountInfo(): AccountInfo {
        return withContext(dispatcherIO) {
            val response = accountApi.getAccountDetails()
            accountMapper.map(response)
        }
    }

    suspend fun setMovieIsFavorite(movieId: Int, isFavorite: Boolean) {
        val request = SetIsFavoriteRequestDto(
            mediaType = MOVIE_MEDIA_TYPE,
            mediaId = movieId,
            isFavorite = isFavorite
        )
        return withContext(dispatcherIO) {
            accountApi.setIsFavorite(
                userId = userPrefs.userId,
                request = request
            )
        }
    }
}
