package com.ranosan.wavies.di

import com.ranosan.wavies.core.domain.usecase.FavoriteMovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun provideFavoriteMovieUseCase(): FavoriteMovieUseCase
}