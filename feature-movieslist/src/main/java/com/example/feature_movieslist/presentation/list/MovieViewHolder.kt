package com.example.feature_movieslist.presentation.list

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.util.getColorFromAttr
import com.example.feature_movieslist.R
import com.example.feature_movieslist.databinding.ItemMovieLineBinding
import com.example.feature_movieslist.domain.MovieEntity

class MovieViewHolder(
    private val movieBinding: ItemMovieLineBinding,
    private val onMoviewClickListener: (MovieEntity) -> Unit
) : RecyclerView.ViewHolder(movieBinding.root) {

    private val placeholder: Drawable

    init {
        placeholder = ColorDrawable(
            itemView.context.getColorFromAttr(R.attr.colorOnBackgroundSecondary)
        )
    }

    fun bind(movie: MovieEntity) {
        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onMoviewClickListener.invoke(movie)
            }
        }
        movieBinding.movieTitle.text = movie.title
        movieBinding.movieSubtitle.text = movie.subtitle
        movieBinding.movieGenre.text = movie.genre
        movieBinding.movieRating.text = movie.rating.toString()
        movieBinding.movieRatingsCount.text = movie.ratingCount.toString()
        movieBinding.movieDuration.text = movie.duration
        Glide.with(itemView)
            .load(movie.posterUrl)
            .placeholder(placeholder)
            .into(movieBinding.moviePoster)
    }
}
