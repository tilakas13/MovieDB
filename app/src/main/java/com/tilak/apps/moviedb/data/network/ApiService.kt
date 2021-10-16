package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.model.castCrew.CastCrewModel
import com.tilak.apps.moviedb.data.model.details.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/3/discover/movie?sort_by=popularity.desc&api_key=3fa9058382669f72dcb18fb405b7a831")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieListModel


    @GET("3/movie/{movie_id}?api_key=3fa9058382669f72dcb18fb405b7a831")
    suspend fun getMovieDetail(@Path("movie_id") movie_id: Int): MovieDetail

    @GET("3/movie/{movie_id}/credits?api_key=3fa9058382669f72dcb18fb405b7a831")
    suspend fun getCastCewDetails(@Path("movie_id") movie_id: Int): CastCrewModel
}