/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.ui.main

import androidx.lifecycle.LiveData
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
@Inject constructor(private val repository: MovieRepository, private val logger: Logger) :
    ViewModel() {


    private var _movieListModel = MutableLiveData<List<MovieModel>>()
    private var totalPages: Int = Int.MAX_VALUE
    private var currentPage: Int = 0

    val listMovies: LiveData<List<MovieModel>>
        get() = _movieListModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPopularMovies() {

        if (currentPage <= totalPages) {
            viewModelScope.launch {
                _isLoading.value = true
                val movieModel = repository.getPopularMovies(++currentPage)
                logger.logInfo("getPopularMovies : $currentPage :: SUCCESS")
                totalPages = movieModel.totalPages
                _movieListModel.value = movieModel.results
                _isLoading.value = false
            }
        }
    }
}