package com.tilak.apps.moviedb.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tilak.apps.moviedb.R
import com.tilak.apps.moviedb.common.AppConstants
import com.tilak.apps.moviedb.data.model.MovieModel
import javax.inject.Inject

class MovieAdapter @Inject constructor() :
    RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

    private var listMovies = ArrayList<MovieModel>()

    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_movie, parent, false)
        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        var movie = listMovies[position]
        val movieBanner = holder.itemView.findViewById<ImageView>(R.id.iv_movie_banner)
        Glide.with(movieBanner.context)
            .load(AppConstants.IMAGE_BASE_URL + movie.posterPath)
            .centerCrop()
            .placeholder(R.drawable.default_movie_place_holder)
            .error(R.drawable.default_movie_place_holder)
            .into(movieBanner)

    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setListMovies(it: List<MovieModel>) {
        listMovies.addAll(it)
        notifyDataSetChanged()
    }
}