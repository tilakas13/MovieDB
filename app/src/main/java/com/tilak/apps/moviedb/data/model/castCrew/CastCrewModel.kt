/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.data.model.castCrew

import com.google.gson.annotations.SerializedName

data class CastCrewModel(

    @SerializedName("id") var id: Int,
    @SerializedName("cast") var cast: List<CastCrew>,
    @SerializedName("crew") var crew: List<CastCrew>

)