/*
 * *
 *  * Created by Tilaka on 28/11/21, 5:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 28/11/21, 5:49 PM
 *
 */

package com.tilak.apps.moviedb.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.tilak.apps.moviedb.data.model.MovieModel

class MovieListDiffCallback(
    private val oldList: List<MovieModel>,
    private val newList: List<MovieModel>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

}