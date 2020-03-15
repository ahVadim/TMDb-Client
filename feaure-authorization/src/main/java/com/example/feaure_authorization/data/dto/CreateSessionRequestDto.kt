package com.example.feaure_authorization.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateSessionRequestDto(
    val request_token: String
)
