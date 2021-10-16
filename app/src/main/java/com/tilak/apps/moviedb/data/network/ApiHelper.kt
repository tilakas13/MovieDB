package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.model.castCrew.CastCrewModel
import com.tilak.apps.moviedb.data.model.details.MovieDetail

interface ApiHelper {

    suspend fun getPopularMovies(page: Int): MovieListModel

    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun getCastCewDetails(movieId: Int): CastCrewModel
}