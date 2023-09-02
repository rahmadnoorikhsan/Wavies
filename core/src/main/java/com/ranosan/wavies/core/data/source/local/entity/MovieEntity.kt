package com.ranosan.wavies.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey
    var id: Int,
    val overview: String? = null,
    var backdropPath: String? = null,
    var originalLanguage: String,
    var releaseDate: String? = null,
    var popularity: Double,
    var voteAverage: Double,
    var title: String,
    var genreIds: List<Int?>,
    var voteCount: Int,
    var posterPath: String? = null,
    var movieType: String,
    var isFavorite: Boolean = false
)