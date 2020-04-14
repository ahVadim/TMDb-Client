package com.example.core.data.session.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteSessionRequestDto(
    @Json(name = "session_id") val sessionId: String
)
