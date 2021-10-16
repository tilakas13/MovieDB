package com.tilak.apps.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class MovieListModel (

   @SerializedName("page") var page : Int,
   @SerializedName("results") var results : List<MovieModel>,
   @SerializedName("total_pages") var totalPages : Int,
   @SerializedName("total_results") var totalResults : Int

)