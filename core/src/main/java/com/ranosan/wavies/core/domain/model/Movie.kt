package com.ranosan.wavies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val overview: String? = null,
    val backdropPath: String? = null,
    val originalLanguage: String,
    val releaseDate: String? = null,
    val popularity: Double,
    val voteAverage: Double,
    val title: String,
    val genreIds: List<Int?>,
    val voteCount: Int,
    val posterPath: String? = null,
    val movieType: String,
    val isFavorite: Boolean
): Parcelable