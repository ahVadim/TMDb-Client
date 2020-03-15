package com.example.feaure_authorization.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestTokenResponseDto(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)
