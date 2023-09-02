package com.ranosan.wavies.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ranosan.wavies.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie_entity WHERE movieType = :type")
    fun getMovies(type: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movie_entity WHERE id = :movieId AND isFavorite = 1)")
    fun isFavoriteMovie(movieId: Int): Flow<Boolean>

    @Query("UPDATE movie_entity SET isFavorite = :isFavorite WHERE id = :movieId")
    suspend fun updateMovie(movieId: Int, isFavorite: Boolean)
}