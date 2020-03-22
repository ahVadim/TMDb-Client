package com.example.core.data.session.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ValidateTokenRequestDto(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
    @Json(name = "request_token") val requestToken: String
)
