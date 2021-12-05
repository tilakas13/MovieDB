/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilak.apps.moviedb.data.model.MovieModel
import com.tilak.apps.moviedb.data.repositories.MovieRepository
import com.tilak.apps.moviedb.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: MovieRepository,
    private val logger: Logger
) : ViewModel() {

    private var _movieListModel = MutableLiveData<MutableList<MovieModel>>()
    private var totalPages: Int = Int.MAX_VALUE
    private var currentPage: Int = 0


    val screenState = MutableLiveData<MovieListingState>()

    fun getPopularMovies() {
        if (currentPage <= totalPages) {
            viewModelScope.launch {
                try {
                    screenState.value = MovieListingState.Loading
                    val movieModel = repository.getPopularMovies(++currentPage)
                    logger.logInfo(TAG, "getPopularMovies : $currentPage :: SUCCESS")
                    totalPages = movieModel.totalPages
                    val movieList =
                        if (_movieListModel.value == null) mutableListOf() else _movieListModel.value
                    movieList?.addAll(movieModel.results)
                    _movieListModel.value = movieList!!
                    screenState.value = MovieListingState.Success(_movieListModel.value!!)
                    logger.logInfo(TAG, "getPopularMovies : ${_movieListModel.value?.size}")
                } catch (e: Exception) {
                    screenState.value = MovieListingState.Error(e.localizedMessage)
                }
            }
        }
    }

    sealed class MovieListingState {
        data class Success(val data: MutableList<MovieModel>) : MovieListingState()
        data class Error(val message: String) : MovieListingState()
        object Loading : MovieListingState()
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}