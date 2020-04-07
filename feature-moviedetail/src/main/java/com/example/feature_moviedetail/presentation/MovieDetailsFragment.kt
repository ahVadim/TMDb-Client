package com.example.feature_moviedetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.core.presentation.BaseFragment
import com.example.feature_moviedetail.databinding.FragmentMoviedetailsBinding

class MovieDetailsFragment : BaseFragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoviedetailsBinding.inflate(inflater, container, false)
        //        binding.movieTitle.text = args.movieName
        return binding.root
    }
}
