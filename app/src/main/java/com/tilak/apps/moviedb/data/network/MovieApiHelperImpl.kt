package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.model.castCrew.CastCrewModel
import com.tilak.apps.moviedb.data.model.details.MovieDetail
import javax.inject.Inject

class MovieApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPopularMovies(page: Int): MovieListModel {
        return apiService.getPopularMovies(page)
    }

    override suspend fun getMovieDetail(movieid: Int): MovieDetail {
        return apiService.getMovieDetail(movieid)
    }

    override suspend fun getCastCewDetails(movieId: Int): CastCrewModel {
        return apiService.getCastCewDetails(movieId)
    }


}