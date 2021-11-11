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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tilak.apps.moviedb.R
import com.tilak.apps.moviedb.common.ImageUtils
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
    lateinit var adapter: CastCrewAdapter

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
        binding.rvCastCrew.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvCastCrew.adapter = adapter

        val movieId = args.movieId
        logger.logInfo("Movie Id : $movieId")
        viewModel.getMovieDetails(movieId)

        viewModel.detailMovie.observe(viewLifecycleOwner, { it ->
            it.let {
                val movieBanner = binding.ivMovieBanner
                Glide.with(movieBanner.context)
                    .load(ImageUtils.getBannerImage(it.backdropPath))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.default_movie_place_holder)
                    .error(R.drawable.default_movie_place_holder)
                    .into(movieBanner)

                binding.toolbar.title = it.originalTitle
                binding.tvMovieDesc.text = it.overview
            }
        })

        viewModel.listCastCrew.observe(viewLifecycleOwner, { it ->
            it.let {
                adapter.setCastCrew(it)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { it ->
            it.let {
                binding.pbLoader.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}