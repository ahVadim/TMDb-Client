package com.example.feaure_authorization.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ValidateTokenRequestDto(
    val username: String,
    val password: String,
    val request_token: String
)
