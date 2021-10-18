/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:44 AM
 *
 */

package com.tilak.apps.moviedb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tilak.apps.moviedb.R
import com.tilak.apps.moviedb.common.AppConstants
import com.tilak.apps.moviedb.data.model.castCrew.CastCrew
import com.tilak.apps.moviedb.databinding.ItemListCastCrewBinding
import javax.inject.Inject

class CastCrewAdapter
@Inject constructor() :
    RecyclerView.Adapter<CastCrewAdapter.CastCrewViewHolder>() {

    private var listCastCrew = ArrayList<CastCrew>()

    class CastCrewViewHolder(var binding: ItemListCastCrewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CastCrewViewHolder {
        val binding =
            ItemListCastCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastCrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastCrewViewHolder, position: Int) {
        val castCrewModel = listCastCrew[position]
        val movieBanner = holder.binding.ivMovieBanner
        Glide.with(movieBanner.context)
            .load(AppConstants.LIST_IMAGE_BASE_URL + castCrewModel.profilePath)
            .centerCrop()
            .placeholder(R.drawable.default_movie_place_holder)
            .error(R.drawable.default_movie_place_holder)
            .into(movieBanner)

        holder.binding.tvCastCrewName.text = castCrewModel.originalName

    }

    override fun getItemCount(): Int {
        return listCastCrew.size
    }

    fun setCastCrew(it: List<CastCrew>) {
        listCastCrew.addAll(it)
        notifyDataSetChanged()
    }
}