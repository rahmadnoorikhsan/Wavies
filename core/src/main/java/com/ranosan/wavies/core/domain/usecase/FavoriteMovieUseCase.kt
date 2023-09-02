package com.ranosan.wavies.core.domain.usecase

import com.ranosan.wavies.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieUseCase {

    fun getFavoriteMovies(): Flow<List<Movie>>

    suspend fun updateMovie(movieId: Int, isFavorite: Boolean)

    fun isFavoriteMovie(movieId: Int): Flow<Boolean>
}