package com.example.feature_movieslist.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.feature_movieslist.databinding.ItemMovieLineBinding
import com.example.feature_movieslist.domain.MovieEntity

class MoviesAdapter : ListAdapter<MovieEntity, MovieViewHolder>(MoviesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieBinding = ItemMovieLineBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
