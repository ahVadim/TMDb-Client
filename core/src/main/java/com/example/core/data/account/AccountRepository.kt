package com.example.core.data.account

import com.example.core.domain.AccountInfo
import com.example.core.mapper.AccountMapper
import com.example.core.network.api.AccountApi
import io.reactivex.Single
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountApi: AccountApi,
    private val accountMapper: AccountMapper
) {

    fun getAccountInfo(): Single<AccountInfo> {
        return accountApi.getAccountDetails()
            .map(accountMapper::map)
    }
}
