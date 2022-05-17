/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.data.model.castCrew

import com.google.gson.annotations.SerializedName

data class CastCrew(

//    @SerializedName("adult") var adult: Boolean,
//    @SerializedName("gender") var gender: Int,
//    @SerializedName("id") var id: Int,
//    @SerializedName("known_for_department") var knownForDepartment: String,
//    @SerializedName("name") var name: String,
    @SerializedName("original_name") var originalName: String,
    //   @SerializedName("popularity") var popularity: Double,
    @SerializedName("profile_path") var profilePath: String?,
    // @SerializedName("cast_id") var castId: Int,
    @SerializedName("character") var character: String,
    //   @SerializedName("credit_id") var creditId: String,
    //  @SerializedName("order") var order: Int,

    // specific to crew
    //  @SerializedName("department") var department: String,
    //  @SerializedName("job") var job: String

)