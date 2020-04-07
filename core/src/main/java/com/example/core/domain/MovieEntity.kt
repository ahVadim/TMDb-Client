package com.example.core.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val posterUrl: String?,
    val title: String,
    val originTitle: String,
    val description: String,
    val genre: String,
    val rating: Double,
    val ratingCount: Int,
    val duration: String?
) : Parcelable
