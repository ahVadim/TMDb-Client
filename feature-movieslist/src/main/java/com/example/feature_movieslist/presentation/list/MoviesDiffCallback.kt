package com.example.feature_movieslist.presentation.list

import androidx.recyclerview.widget.DiffUtil

class MoviesDiffCallback : DiffUtil.ItemCallback<MovieItemViewModel>() {
    override fun areItemsTheSame(
        oldItem: MovieItemViewModel,
        newItem: MovieItemViewModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieItemViewModel,
        newItem: MovieItemViewModel
    ): Boolean {
        return oldItem == newItem
    }
}
