package com.example.core.data.session.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateSessionRequestDto(
    @Json(name = "request_token") val requestToken: String
)
