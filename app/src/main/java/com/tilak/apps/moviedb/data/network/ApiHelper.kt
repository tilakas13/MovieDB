package com.tilak.apps.moviedb.data.network

import com.tilak.apps.moviedb.data.model.MovieListModel

interface ApiHelper {

    suspend fun getPopularMovies(page :Int): MovieListModel
}