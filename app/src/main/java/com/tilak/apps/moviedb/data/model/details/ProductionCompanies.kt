/*
 * *
 *  * Created by Tilaka on 10/18/21, 10:52 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 10/18/21, 10:12 AM
 *
 */

package com.tilak.apps.moviedb.data.model.details

import com.google.gson.annotations.SerializedName

data class ProductionCompanies(

    @SerializedName("id") var id: Int,
    @SerializedName("logo_path") var logoPath: String,
    @SerializedName("name") var name: String,
    @SerializedName("origin_country") var originCountry: String

)