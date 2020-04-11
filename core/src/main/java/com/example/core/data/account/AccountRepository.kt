package com.example.core.data.account

import com.example.core.data.account.dto.SetIsFavoriteRequestDto
import com.example.core.domain.AccountInfo
import com.example.core.mapper.AccountMapper
import com.example.core.network.api.AccountApi
import com.example.core.prefs.UserPrefs
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountApi: AccountApi,
    private val accountMapper: AccountMapper,
    private val userPrefs: UserPrefs
) {

    companion object {
        private const val MOVIE_MEDIA_TYPE = "movie"
    }

    fun getAccountInfo(): Single<AccountInfo> {
        return accountApi.getAccountDetails()
            .map(accountMapper::map)
    }

    fun setMovieIsFavorite(movieId: Int, isFavorite: Boolean): Completable {
        val request = SetIsFavoriteRequestDto(
            mediaType = MOVIE_MEDIA_TYPE,
            mediaId = movieId,
            isFavorite = isFavorite
        )
        return accountApi.setIsFavorite(
            userId = userPrefs.userId,
            request = request
        )
    }
}
