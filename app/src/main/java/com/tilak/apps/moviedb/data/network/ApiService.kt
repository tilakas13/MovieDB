package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/discover/movie?sort_by=popularity.desc&api_key=3fa9058382669f72dcb18fb405b7a831")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieListModel
}