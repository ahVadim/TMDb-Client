package com.example.core.data.account.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SetIsFavoriteRequestDto(
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "media_id") val mediaId: Int,
    @Json(name = "favorite") val isFavorite: Boolean
)
