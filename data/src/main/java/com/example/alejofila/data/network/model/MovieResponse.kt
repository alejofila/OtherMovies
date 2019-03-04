package com.example.alejofila.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int,
    @SerializedName("total_results")val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<MovieEntity>
)


data class MovieEntity(
    val title: String,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterImage: String
)