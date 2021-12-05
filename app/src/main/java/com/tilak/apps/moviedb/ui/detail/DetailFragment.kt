/*
 * *
 *  * Created by Tilaka on 11/11/21, 10:43 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/11/21, 10:39 AM
 *
 */

package com.tilak.apps.moviedb.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tilak.apps.moviedb.R
import com.tilak.apps.moviedb.common.ImageUtils
import com.tilak.apps.moviedb.data.model.details.MovieDetail
import com.tilak.apps.moviedb.databinding.DetailFragmentBinding
import com.tilak.apps.moviedb.ui.base.BaseFragment
import com.tilak.apps.moviedb.utils.Logger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var castCrewAdapter: CastCrewAdapter

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCastCrew.apply {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = castCrewAdapter
        }

        val movieId = args.movieId
        logger.logInfo(TAG, "Movie Id : $movieId")
        viewModel.getMovieDetails(movieId)
        observeStateChange()
    }

    private fun observeStateChange() {
        viewModel.screenState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailViewModel.DetailViewState.SuccessMovieDetail -> {
                    displayMovieDetail(it.data)
                }
                is DetailViewModel.DetailViewState.SuccessCastCrew -> {
                    castCrewAdapter.setCastCrew(it.data)
                }
                is DetailViewModel.DetailViewState.Loading -> {
                    binding.pbLoader.visibility = if (it.stateLoading) View.VISIBLE else View.GONE
                }
                is DetailViewModel.DetailViewState.Error -> {
                    Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun displayMovieDetail(data: MovieDetail) {
        data.let {
            binding.toolbar.title = it.originalTitle
            binding.tvMovieDesc.text = data.overview
            val movieBanner = binding.ivMovieBanner
            Glide.with(movieBanner.context)
                .load(ImageUtils.getBannerImage(it.backdropPath))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.default_movie_place_holder)
                .error(R.drawable.default_movie_place_holder)
                .into(movieBanner)
        }
    }

    override fun onDestroy() {
        binding.rvCastCrew.adapter = null
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val TAG = "DetailFragment"
    }

}