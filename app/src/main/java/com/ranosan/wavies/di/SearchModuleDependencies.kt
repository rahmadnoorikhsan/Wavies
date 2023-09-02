package com.ranosan.wavies.di

import com.ranosan.wavies.core.domain.usecase.MoviePagingUseCase
import com.ranosan.wavies.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SearchModuleDependencies {
    fun provideMovieUseCase(): MovieUseCase

    fun provideMoviePagingUseCase(): MoviePagingUseCase
}