/*
 * *
 *  * Created by Tilaka on 11/11/21, 10:33 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 11/11/21, 10:33 AM
 *
 */

package com.tilak.apps.moviedb.common

import javax.annotation.Nonnull

object ImageUtils {

    fun getListThumbnail(@Nonnull posterPath: String): String {
        return "${AppConstants.LIST_IMAGE_BASE_URL}$posterPath"
    }

    fun getBannerImage(@Nonnull posterPath: String): String {
        return "${AppConstants.LIST_IMAGE_BASE_URL}$posterPath"
    }
}