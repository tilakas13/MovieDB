/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilak.apps.moviedb.data.model.castCrew.CastCrew
import com.tilak.apps.moviedb.data.model.details.MovieDetail
import com.tilak.apps.moviedb.data.repositories.MovieRepository
import com.tilak.apps.moviedb.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository, private val logger: Logger
) : ViewModel() {

    private var _detailMovieModel = MutableLiveData<MovieDetail>()
    private var _listCastCrew = MutableLiveData<List<CastCrew>>()
    val screenState = MutableLiveData<DetailViewState>()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                screenState.value = DetailViewState.Loading(true)
                val detail = repository.getMovieDetails(movieId)
                val castCrewModel = repository.getCastCewDetails(movieId)
                _detailMovieModel.value = detail
                screenState.value = DetailViewState.SuccessMovieDetail(detail)
                screenState.value = DetailViewState.Loading(false)
                val listCast = castCrewModel.cast as ArrayList<CastCrew>
                listCast.addAll(castCrewModel.crew)
                _listCastCrew.value = listCast
                screenState.value = DetailViewState.SuccessCastCrew(listCast)
                logger.logInfo(TAG, "Total cast & crew :${listCast.size}")
            } catch (e: Exception) {
                screenState.value = DetailViewState.Loading(false)
                screenState.value = DetailViewState.Error(
                    e.localizedMessage ?: "Please check your internet connection"
                )
            }
        }

    }

    sealed class DetailViewState {
        data class SuccessMovieDetail(val data: MovieDetail) : DetailViewState()
        data class SuccessCastCrew(val data: ArrayList<CastCrew>) : DetailViewState()
        data class Error(val message: String) : DetailViewState()
        data class Loading(val stateLoading: Boolean) : DetailViewState()
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}