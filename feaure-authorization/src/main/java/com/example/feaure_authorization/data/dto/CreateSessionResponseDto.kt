package com.example.feaure_authorization.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateSessionResponseDto(
    val success: Boolean,
    val session_id: String
)
