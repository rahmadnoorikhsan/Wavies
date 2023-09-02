package com.ranosan.wavies.core.di

import com.ranosan.wavies.core.data.repository.MoviePagingRepositoryImpl
import com.ranosan.wavies.core.data.repository.MovieRepositoryImpl
import com.ranosan.wavies.core.domain.repository.MoviePagingRepository
import com.ranosan.wavies.core.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun provideMoviePagingRepository(moviePagingRepositoryImpl: MoviePagingRepositoryImpl): MoviePagingRepository
}