/*
 * *
 *  * Created by Tilaka on 5/16/22, 9:44 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 5/16/22, 9:44 AM
 *
 */

package com.tilak.apps.moviedb.domain.castCrew

import com.tilak.apps.moviedb.data.model.castCrew.CastCrewModel
import com.tilak.apps.moviedb.data.repositories.MovieRepository
import javax.inject.Inject

class CastCrewUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun getCastCewDetails(movieId: Int): CastCrewModel {
        return repository.getCastCewDetails(movieId)
    }
}