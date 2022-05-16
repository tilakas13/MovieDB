/*
 * *
 *  * Created by Tilaka on 5/16/22, 9:44 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 5/16/22, 9:44 AM
 *
 */

package com.tilak.apps.moviedb.domain.movieList

import com.tilak.apps.moviedb.data.model.MovieListModel
import com.tilak.apps.moviedb.data.model.details.MovieDetail
import com.tilak.apps.moviedb.data.repositories.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun getPopularMovies(page: Int): MovieListModel {
        return repository.getPopularMovies(page)
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetail {
        return repository.getMovieDetails(movieId)
    }
}