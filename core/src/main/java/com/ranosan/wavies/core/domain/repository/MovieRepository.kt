package com.ranosan.wavies.core.domain.repository

import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.data.repository.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTrendingMovies(): Flow<Resource<List<Movie>>>

    fun getMoviesNowPlaying(): Flow<Resource<List<Movie>>>

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun isFavoriteMovie(movieId: Int): Flow<Boolean>

    suspend fun insertMovie(movie: Movie)

    suspend fun updateMovie(movieId: Int, isFavorite: Boolean)
}