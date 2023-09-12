package com.ranosan.wavies.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ranosan.wavies.core.data.source.local.entity.MovieEntity
import com.ranosan.wavies.core.utils.Converters

@Database(
    entities = [MovieEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}