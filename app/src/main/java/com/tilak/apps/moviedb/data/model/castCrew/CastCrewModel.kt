package com.tilak.apps.moviedb.data.model.castCrew

import com.google.gson.annotations.SerializedName

data class CastCrewModel(

    @SerializedName("id") var id: Int,
    @SerializedName("cast") var cast: List<CastCrew>,
    @SerializedName("crew") var crew: List<CastCrew>

)