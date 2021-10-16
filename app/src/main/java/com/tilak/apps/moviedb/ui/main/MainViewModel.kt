package com.tilak.apps.moviedb.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilak.apps.moviedb.data.model.MovieModel
import com.tilak.apps.moviedb.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val TAG = "MainViewModel"

    private var _movieListModel = MutableLiveData<List<MovieModel>>()
    private var totalPages: Int = Int.MAX_VALUE
    private var currentPage: Int = 0

    val listMovies: LiveData<List<MovieModel>>
        get() = _movieListModel

    fun getPopularMovies(currentPage: Int) {
        if (currentPage <= currentPage) {
            viewModelScope.launch {
                val movieModel = repository.getPopularMovies(currentPage)
                Log.i(TAG, "Fetched user $movieModel")
                totalPages = movieModel.totalPages
                _movieListModel.value = movieModel.results
                this@MainViewModel.currentPage = currentPage
            }
        }
    }
}