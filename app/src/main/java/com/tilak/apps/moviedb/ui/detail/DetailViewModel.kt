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
import com.tilak.apps.moviedb.domain.castCrew.CastCrewUseCase
import com.tilak.apps.moviedb.domain.movieList.MovieUseCase
import com.tilak.apps.moviedb.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val castCrewUseCase: CastCrewUseCase,
    private val logger: Logger
) : ViewModel() {

    private var _detailMovieModel = MutableLiveData<MovieDetail>()
    private var _listCastCrew = MutableLiveData<List<CastCrew>>()
    val screenState = MutableLiveData<DetailViewState>()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(exceptionHandler) {
            screenState.value = DetailViewState.Loading(true)
            val detail = movieUseCase.getMovieDetails(movieId)
            val castCrewModel = castCrewUseCase.getCastCewDetails(movieId)
            _detailMovieModel.value = detail
            screenState.value = DetailViewState.SuccessMovieDetail(detail)
            screenState.value = DetailViewState.Loading(false)
            val listCast = castCrewModel.cast as ArrayList<CastCrew>
            listCast.addAll(castCrewModel.crew)
            _listCastCrew.value = listCast
            screenState.value = DetailViewState.SuccessCastCrew(listCast)
            logger.logInfo(TAG, "Total cast & crew :${listCast.size}")
        }

    }

    sealed class DetailViewState {
        data class SuccessMovieDetail(val data: MovieDetail) : DetailViewState()
        data class SuccessCastCrew(val data: ArrayList<CastCrew>) : DetailViewState()
        data class Error(val message: String) : DetailViewState()
        data class Loading(val stateLoading: Boolean) : DetailViewState()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        screenState.value = DetailViewState.Loading(false)
        screenState.value = DetailViewState.Error(
            exception.localizedMessage ?: "Please check your internet connection"
        )
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}