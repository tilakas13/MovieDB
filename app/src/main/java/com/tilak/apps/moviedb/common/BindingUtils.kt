/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtils {

    @JvmStatic
    @BindingAdapter("imageUrl", "placeHolder")
    fun loadImage(view: ImageView, url: String, placeHolder: Drawable) {
        Glide.with(view.context)
            .load(ImageUtils.getListThumbnail(url))
            .centerCrop()
            .placeholder(placeHolder)
            .error(placeHolder)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("setRating")
    fun setRating(view: TextView, ratingMovie: Double) {
        view.text = "$ratingMovie"
    }
}