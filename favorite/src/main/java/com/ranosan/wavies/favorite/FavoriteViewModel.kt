package com.ranosan.wavies.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ranosan.wavies.core.domain.usecase.FavoriteMovieUseCase

class FavoriteViewModel(private val favoriteMovieUseCase: FavoriteMovieUseCase) : ViewModel() {

    fun favoriteMovies() = favoriteMovieUseCase.getFavoriteMovies().asLiveData()
}