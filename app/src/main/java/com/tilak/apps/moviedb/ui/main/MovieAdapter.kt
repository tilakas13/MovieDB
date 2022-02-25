/*
 * *
 *  * Created by Tilaka on 2/25/22, 5:04 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 2/25/22, 5:04 PM
 *
 */

package com.tilak.apps.moviedb.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tilak.apps.moviedb.BR
import com.tilak.apps.moviedb.data.model.MovieModel
import com.tilak.apps.moviedb.databinding.ItemListMovieBinding
import com.tilak.apps.moviedb.utils.Logger
import javax.inject.Inject

class MovieAdapter
@Inject constructor(val logger: Logger) :
    RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

    private var listMovies = mutableListOf<MovieModel>()

    class MovieItemViewHolder(var binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val binding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movieModel = listMovies[position]
        holder.binding.setVariable(BR.movieModel, movieModel)
        holder.binding.root.setOnClickListener { view ->
            val actionDetailView =
                MainFragmentDirections.actionMainFragmentToDetailFragment(movieModel.id)
            view.findNavController().navigate(actionDetailView)
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setListMovies(it: List<MovieModel>) {
        logger.logInfo(TAG, "setListMovies ${it.size}")
        val diffCallback = MovieListDiffCallback(this.listMovies, it)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listMovies.clear()
        this.listMovies.addAll(it)
        diffResult.dispatchUpdatesTo(this)
    }

    companion object {
        private const val TAG = "MovieAdapter"
    }
}