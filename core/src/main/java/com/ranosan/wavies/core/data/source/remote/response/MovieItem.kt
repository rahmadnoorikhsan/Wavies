package com.ranosan.wavies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int?>,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("poster_path")
    val posterPath: String? = null
)
