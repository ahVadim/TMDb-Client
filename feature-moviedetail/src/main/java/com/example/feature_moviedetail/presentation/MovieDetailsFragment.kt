package com.example.feature_moviedetail.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.core.di.CoreComponentHolder
import com.example.core.presentation.BaseFragment
import com.example.core.util.observe
import com.example.feature_moviedetail.databinding.FragmentMoviedetailsBinding
import com.example.feature_moviedetail.di.DaggerMoviesDetailsComponent
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentMoviedetailsBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        DaggerMoviesDetailsComponent.factory()
            .create(CoreComponentHolder.coreComponent)
            .inject(this)
        super.onAttach(context)
        movieDetailsViewModel.setMovie(args.movieEntity)
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
        observe(movieDetailsViewModel.liveState, ::renderState)
    }

    private fun renderState(state: MovieDetailsViewState) {
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
