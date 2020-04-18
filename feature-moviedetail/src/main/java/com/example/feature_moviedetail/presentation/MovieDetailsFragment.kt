package com.example.feature_moviedetail.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.badoo.mvicore.modelWatcher
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.core.di.CoreComponentHolder
import com.example.core.domain.MovieEntity
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.core.util.viewModel
import com.example.feature_moviedetail.R
import com.example.feature_moviedetail.databinding.FragmentMoviedetailsBinding
import com.example.feature_moviedetail.di.DaggerMoviesDetailsComponent
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: MovieDetailsViewModel.Factory
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel {
        viewModelFactory.create(args.movieEntity)
    }

    private var _binding: FragmentMoviedetailsBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerMoviesDetailsComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviedetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addFavoriteButton.setOnClickListener{ movieDetailsViewModel.onAddFavoriteButtonClick() }
        observe(movieDetailsViewModel.liveState, stateWatcher::invoke)
    }

    private val stateWatcher = modelWatcher<MovieDetailsViewState> {
        watch(MovieDetailsViewState::movie) { showMovie(it) }
        watch(MovieDetailsViewState::isFavorite) { binding.addFavoriteButton.isActivated = it }
    }

    private fun showMovie(movie: MovieEntity) {
        val posterCornerRadius = resources
            .getDimensionPixelSize(R.dimen.poster_corner_radius)
        with(binding) {
            movieTitle.text = movie.title
            movieSubtitle.text = movie.originTitle
            movieGenre.text = movie.genre
            movieRating.text = movie.rating.toString()
            movieRatingsCount.text = movie.ratingCount.toString()
            movieDuration.text = movie.duration
            movieDescription.text = movie.description
            Glide.with(root)
                .load(movie.posterUrl)
                .apply(
                    RequestOptions()
                        .transform(RoundedCorners(posterCornerRadius))
                        .placeholder(R.drawable.movie_placeholder)
                )
                .into(moviePoster)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
