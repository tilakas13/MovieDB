package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.model.castCrew.CastCrewModel
import com.tilak.apps.moviedb.data.model.details.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/3/discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieListModel


    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movie_id: Int): MovieDetail

    @GET("3/movie/{movie_id}/credits")
    suspend fun getCastCewDetails(@Path("movie_id") movie_id: Int): CastCrewModel
}