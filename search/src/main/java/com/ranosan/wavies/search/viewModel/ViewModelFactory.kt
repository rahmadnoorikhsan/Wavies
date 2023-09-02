package com.ranosan.wavies.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranosan.wavies.core.domain.usecase.MoviePagingUseCase
import com.ranosan.wavies.core.domain.usecase.MovieUseCase
import com.ranosan.wavies.search.SearchViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val moviePagingUseCase: MoviePagingUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(movieUseCase, moviePagingUseCase) as T
            else -> throw IllegalArgumentException("Unknown VieModel class name ${modelClass.name}")
        }
}