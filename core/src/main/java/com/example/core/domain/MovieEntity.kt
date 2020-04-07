package com.example.core.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val posterUrl: String?,
    val title: String,
    val subtitle: String,
    val genre: String,
    val rating: Double,
    val ratingCount: Int,
    val duration: String?
) : Parcelable
