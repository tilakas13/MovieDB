package com.tilak.apps.moviedb.data.repositories

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.network.ApiHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPopularMovies(page: Int): MovieListModel {
        return apiHelper.getPopularMovies(page)
    }


}