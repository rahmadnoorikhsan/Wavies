package com.ranosan.wavies.core.domain.usecase

import com.ranosan.wavies.core.data.repository.Resource
import com.ranosan.wavies.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getTrendingMovies(): Flow<Resource<List<Movie>>>

    fun getMoviesNowPlaying(): Flow<Resource<List<Movie>>>

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>

    suspend fun insertMovie(movie: Movie)
}