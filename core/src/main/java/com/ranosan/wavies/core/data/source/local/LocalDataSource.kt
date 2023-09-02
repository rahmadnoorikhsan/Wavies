package com.ranosan.wavies.core.data.source.local

import com.ranosan.wavies.core.data.source.local.entity.MovieEntity
import com.ranosan.wavies.core.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    suspend fun insertMovie(movie: MovieEntity) = movieDao.insertMovie(movie)

    fun getMovies(type: String) = movieDao.getMovies(type)

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    fun isFavoriteMovie(movieId: Int) = movieDao.isFavoriteMovie(movieId)

    suspend fun updateMovie(movieId: Int, isFavorite: Boolean) = movieDao.updateMovie(movieId, isFavorite)
}