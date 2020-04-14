package com.example.feature_movieslist.presentation.list

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_movieslist.domain.MovieEntity

class MoviesDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(
        oldItem: MovieEntity,
        newItem: MovieEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieEntity,
        newItem: MovieEntity
    ): Boolean {
        return oldItem == newItem
    }
}
