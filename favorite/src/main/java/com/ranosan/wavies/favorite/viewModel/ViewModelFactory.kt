package com.ranosan.wavies.favorite.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranosan.wavies.core.domain.usecase.FavoriteMovieUseCase
import com.ranosan.wavies.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(favoriteMovieUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }
}