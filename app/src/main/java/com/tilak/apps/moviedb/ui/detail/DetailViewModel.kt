package com.tilak.apps.moviedb.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilak.apps.moviedb.common.AppConstants
import com.tilak.apps.moviedb.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {


    fun getMovieDetails(movieId: Int) {

        viewModelScope.launch {

            var detail = repository.getMovieDetails(movieId)
            var castCrewModel = repository.getCastCewDetails(movieId)

            Log.i(AppConstants.TAG, "getMovieDetails:$detail ")
            Log.i(AppConstants.TAG, "get cast & crew:$castCrewModel ")
        }

    }


}