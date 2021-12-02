/*
 * *
 *  * Created by Tilaka on 12/2/21, 3:53 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/11/21, 10:40 AM
 *
 */

package com.tilak.apps.moviedb.common

object ImageUtils {

    fun getListThumbnail(posterPath: String): String {
        return "${AppConstants.LIST_IMAGE_BASE_URL}$posterPath"
    }

    fun getBannerImage(posterPath: String): String {
        return "${AppConstants.LIST_IMAGE_BASE_URL}$posterPath"
    }
}