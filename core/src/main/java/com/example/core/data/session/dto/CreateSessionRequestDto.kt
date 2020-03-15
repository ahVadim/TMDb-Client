package com.example.core.data.session.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateSessionRequestDto(
    val request_token: String
)
