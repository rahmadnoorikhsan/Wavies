package com.ranosan.wavies.core.domain.usecase

import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : FavoriteMovieUseCase {
    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override suspend fun updateMovie(movieId: Int, isFavorite: Boolean) = movieRepository.updateMovie(movieId, isFavorite)

    override fun isFavoriteMovie(movieId: Int) = movieRepository.isFavoriteMovie(movieId)
}