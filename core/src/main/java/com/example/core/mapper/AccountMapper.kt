package com.example.core.mapper

import com.example.core.data.account.dto.GetAccountResponseDto
import com.example.core.domain.AccountInfo
import javax.inject.Inject

class AccountMapper @Inject constructor() {
    fun map(accountDto: GetAccountResponseDto): AccountInfo {
        return AccountInfo(
            id = accountDto.id,
            name = accountDto.name
        )
    }
}
