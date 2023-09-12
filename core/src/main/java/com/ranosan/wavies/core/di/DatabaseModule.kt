package com.ranosan.wavies.core.di

import android.content.Context
import androidx.room.Room
import com.ranosan.wavies.core.data.source.local.room.MovieDao
import com.ranosan.wavies.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val passphrase = SQLiteDatabase.getBytes("wavies".toCharArray())
    val factory = SupportFactory(passphrase)

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao =
        movieDatabase.getMovieDao()
}