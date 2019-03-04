package com.example.alejofila.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val result: List<MovieEntity>
)


data class MovieEntity(
    val title: String,
    val overview: String,
    @SerializedName("release_data") val releaseDate: String,
    @SerializedName("poster_path") val posterImage: String
)