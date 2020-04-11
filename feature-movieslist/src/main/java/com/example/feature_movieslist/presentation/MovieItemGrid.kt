package com.example.feature_movieslist.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.core.domain.MovieEntity
import com.example.feature_movieslist.R
import com.example.feature_movieslist.databinding.ItemMovieGridBinding
import com.xwray.groupie.viewbinding.BindableItem

data class MovieItemGrid(
    private val movie: MovieEntity,
    private val onMoviewClickListener: (MovieEntity) -> Unit
) : BindableItem<ItemMovieGridBinding>() {

    override fun getLayout() = R.layout.item_movie_grid

    override fun initializeViewBinding(view: View): ItemMovieGridBinding {
        return ItemMovieGridBinding.bind(view)
    }

    override fun getId() = movie.id.toLong()

    override fun bind(viewBinding: ItemMovieGridBinding, position: Int) {
        with(viewBinding) {
            root.setOnClickListener {
                if (position != RecyclerView.NO_POSITION) {
                    onMoviewClickListener.invoke(movie)
                }
            }
            movieTitle.text = movie.title
            movieSubtitle.text = movie.originTitle
            movieGenre.text = movie.genre
            movieRating.text = movie.rating.toString()
            movieRatingsCount.text = movie.ratingCount.toString()
            movieDuration.text = movie.duration
        }

        val posterCornerRadius = viewBinding.root.resources
            .getDimensionPixelSize(R.dimen.poster_corner_radius)
        Glide.with(viewBinding.root)
            .load(movie.posterUrl)
            .apply(
                RequestOptions()
                    .transform(RoundedCorners(posterCornerRadius))
                    .placeholder(R.drawable.movie_placeholder)
            )
            .into(viewBinding.moviePoster)
    }
}
