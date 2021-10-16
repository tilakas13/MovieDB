package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel
import javax.inject.Inject

class MovieApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPopularMovies(page: Int): MovieListModel {
        return apiService.getPopularMovies(page)
    }


}