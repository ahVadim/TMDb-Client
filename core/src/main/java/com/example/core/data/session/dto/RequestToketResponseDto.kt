package com.example.core.data.session.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestTokenResponseDto(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)
