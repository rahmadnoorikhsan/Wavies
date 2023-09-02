package com.ranosan.wavies.core.domain.usecase

import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.domain.repository.MovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository) : MovieUseCase {

    override fun getTrendingMovies() = movieRepository.getTrendingMovies()

    override fun getMoviesNowPlaying() = movieRepository.getMoviesNowPlaying()

    override fun getPopularMovies() = movieRepository.getPopularMovies()

    override fun getUpcomingMovies() = movieRepository.getUpcomingMovies()

    override fun getTopRatedMovies() = movieRepository.getTopRatedMovies()

    override suspend fun insertMovie(movie: Movie) = movieRepository.insertMovie(movie)
}