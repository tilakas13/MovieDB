/*
 * *
 *  * Created by Tilaka on 5/13/22, 9:21 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 5/13/22, 9:21 PM
 *
 */

package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.model.castCrew.CastCrewModel
import com.tilak.apps.moviedb.data.model.details.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieListModel

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movie_id: Int): MovieDetail

    @GET("movie/{movie_id}/credits")
    suspend fun getCastCewDetails(@Path("movie_id") movie_id: Int): CastCrewModel
}