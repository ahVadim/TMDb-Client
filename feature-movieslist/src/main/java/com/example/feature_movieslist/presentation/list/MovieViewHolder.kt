package com.example.feature_movieslist.presentation.list

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_movieslist.databinding.ItemMovieLineBinding
import com.example.feature_movieslist.domain.MovieEntity

class MovieViewHolder(
    private val movieBinding: ItemMovieLineBinding
) : RecyclerView.ViewHolder(movieBinding.root) {

    fun bind(viewModel: MovieEntity) {
        movieBinding.movieTitle.text = viewModel.title
        movieBinding.movieSubtitle.text = viewModel.subtitle
        movieBinding.movieGenre.text = viewModel.genre
        movieBinding.movieRating.text = viewModel.rating.toString()
        movieBinding.movieRatingsCount.text = viewModel.ratingCount.toString()
        movieBinding.movieDuration.text = viewModel.duration
    }
}
