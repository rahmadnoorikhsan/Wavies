package com.ranosan.wavies.core.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.ranosan.wavies.core.data.source.local.entity.MovieEntity
import com.ranosan.wavies.core.data.source.remote.response.MovieItem
import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.domain.model.MovieType

object DataMapper {
    fun mapGenreIdToGenre(id: List<Int?>): List<String?> =
        id.map { genre ->
            when (genre) {
                28 -> "Action"
                12 -> "Adventure"
                16 -> "Animation"
                35 -> "Comedy"
                80 -> "Crime"
                99 -> "Documentary"
                18 -> "Drama"
                10751 -> "Family"
                14 -> "Fantasy"
                36 -> "History"
                27 -> "Horror"
                10402 -> "Music"
                9648 -> "Mystery"
                10749 -> "Romance"
                878 -> "Science Fiction"
                10770 -> "TV Movie"
                53 -> "Thriller"
                10752 -> "War"
                37 -> "Western"
                else -> null
            }
        }

    fun mapMovieResponseToEntity(input: List<MovieItem>, movieType: MovieType): List<MovieEntity> =
        input.map {
            MovieEntity(
                it.id,
                it.overview,
                it.backdropPath,
                it.originalLanguage,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.title,
                it.genreIds,
                it.voteCount,
                it.posterPath,
                movieType.name,
                false
            )
        }

    fun mapMovieEntitiesToMovieDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.overview,
                it.backdropPath,
                it.originalLanguage,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.title,
                it.genreIds,
                it.voteCount,
                it.posterPath,
                it.movieType,
                it.isFavorite
            )
        }

    fun mapPagingResponseToPagingDomain(
        input: PagingData<MovieItem>,
        movieType: MovieType
    ): PagingData<Movie> =
        input.map {
            Movie(
                it.id,
                it.overview,
                it.backdropPath,
                it.originalLanguage,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.title,
                it.genreIds,
                it.voteCount,
                it.posterPath,
                movieType.name,
                false
            )
        }

    fun mapMovieDomainToEntity(it: Movie): MovieEntity =
        MovieEntity(
            it.id,
            it.overview,
            it.backdropPath,
            it.originalLanguage,
            it.releaseDate,
            it.popularity,
            it.voteAverage,
            it.title,
            it.genreIds,
            it.voteCount,
            it.posterPath,
            it.movieType,
            it.isFavorite
        )
}