package com.ranosan.wavies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ranosan.wavies.core.domain.usecase.FavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteMovieUseCase: FavoriteMovieUseCase
): ViewModel() {

    fun update(movieId: Int, isFavorite: Boolean) = viewModelScope.launch {
        favoriteMovieUseCase.updateMovie(movieId, isFavorite)
    }

    fun isFavorite(movieId: Int) = favoriteMovieUseCase.isFavoriteMovie(movieId).asLiveData()
}