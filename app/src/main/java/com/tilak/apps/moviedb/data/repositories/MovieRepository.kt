package com.tilak.apps.moviedb.data.repositories

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.model.castCrew.CastCrewModel
import com.tilak.apps.moviedb.data.model.details.MovieDetail
import com.tilak.apps.moviedb.data.network.ApiHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPopularMovies(page: Int): MovieListModel {
        return apiHelper.getPopularMovies(page)
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetail {
        return apiHelper.getMovieDetail(movieId)
    }


    suspend fun getCastCewDetails(movieId: Int): CastCrewModel {
        return apiHelper.getCastCewDetails(movieId)
    }


}