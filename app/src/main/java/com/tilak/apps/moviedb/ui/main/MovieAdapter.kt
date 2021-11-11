/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:51 AM
 *
 */

package com.tilak.apps.moviedb.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tilak.apps.moviedb.R
import com.tilak.apps.moviedb.common.AppConstants
import com.tilak.apps.moviedb.common.ImageUtils
import com.tilak.apps.moviedb.data.model.MovieModel
import com.tilak.apps.moviedb.databinding.ItemListMovieBinding
import javax.inject.Inject

class MovieAdapter
@Inject constructor() :
    RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

    private var listMovies = ArrayList<MovieModel>()

    class MovieItemViewHolder(var binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = listMovies[position]
        val movieBanner = holder.binding.ivMovieBanner
        Glide.with(movieBanner.context)
            .load(ImageUtils.getListThumbnail(movie.posterPath))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.default_movie_place_holder)
            .error(R.drawable.default_movie_place_holder)
            .into(movieBanner)
        holder.binding.tvRating.text = movie.voteAverage.toString()
        holder.binding.root.setOnClickListener { view ->
            val actionDetailView =
                MainFragmentDirections.actionMainFragmentToDetailFragment(movie.id)
            view.findNavController().navigate(actionDetailView)
        }

    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setListMovies(it: List<MovieModel>) {
        listMovies.addAll(it)
        notifyDataSetChanged()
    }
}