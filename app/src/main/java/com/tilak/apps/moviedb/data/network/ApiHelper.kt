/*
 * *
 *  * Created by Tilaka on 5/13/22, 9:19 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 10/23/21, 6:24 PM
 *
 */

package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.model.castCrew.CastCrewModel
import com.tilak.apps.moviedb.data.model.details.MovieDetail

interface ApiHelper {

    suspend fun getPopularMovies(page: Int): MovieListModel

    suspend fun getMovieDetail(movieId: Int): MovieDetail

    suspend fun getCastCewDetails(movieId: Int): CastCrewModel
}