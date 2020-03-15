package com.example.core.data.session.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateSessionResponseDto(
    val success: Boolean,
    val session_id: String
)
