package com.example.core.data.session.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ValidateTokenRequestDto(
    val username: String,
    val password: String,
    val request_token: String
)
