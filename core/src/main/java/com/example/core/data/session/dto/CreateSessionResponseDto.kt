package com.example.core.data.session.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateSessionResponseDto(
    @Json(name = "success") val success: Boolean,
    @Json(name = "session_id") val sessionId: String
)
