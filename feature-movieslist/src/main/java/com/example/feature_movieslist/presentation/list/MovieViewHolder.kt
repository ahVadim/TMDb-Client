package com.example.feature_movieslist.presentation.list

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_movieslist.R
import com.example.feature_movieslist.databinding.ItemMovieLineBinding
import com.example.feature_movieslist.domain.MovieEntity

class MovieViewHolder(
    private val movieBinding: ItemMovieLineBinding
) : RecyclerView.ViewHolder(movieBinding.root) {

    private val placeholder: Drawable

    init {
        placeholder = ColorDrawable(
            ContextCompat.getColor(itemView.context, R.color.dark_blue)
        )
    }

    fun bind(viewModel: MovieEntity) {
        movieBinding.movieTitle.text = viewModel.title
        movieBinding.movieSubtitle.text = viewModel.subtitle
        movieBinding.movieGenre.text = viewModel.genre
        movieBinding.movieRating.text = viewModel.rating.toString()
        movieBinding.movieRatingsCount.text = viewModel.ratingCount.toString()
        movieBinding.movieDuration.text = viewModel.duration
        Glide.with(itemView)
            .load(viewModel.posterUrl)
            .placeholder(placeholder)
            .into(movieBinding.moviePoster)
    }
}
