package com.tilak.apps.moviedb.ui.detail

import androidx.lifecycle.LiveData
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

    val detailMovie: LiveData<MovieDetail>
        get() = _detailMovieModel

    val listCastCrew: LiveData<List<CastCrew>>
        get() = _listCastCrew

    fun getMovieDetails(movieId: Int) {

        viewModelScope.launch {

            val detail = repository.getMovieDetails(movieId)
            val castCrewModel = repository.getCastCewDetails(movieId)

            _detailMovieModel.value = detail


            val listCast = castCrewModel.cast as ArrayList<CastCrew>
            listCast.addAll(castCrewModel.crew)

            _listCastCrew.value = listCast

            logger.logInfo("Total cast & crew :${listCast.size}")
        }

    }


}