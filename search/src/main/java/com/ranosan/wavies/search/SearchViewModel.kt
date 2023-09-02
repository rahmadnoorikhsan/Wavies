package com.ranosan.wavies.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.domain.usecase.MoviePagingUseCase
import com.ranosan.wavies.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val moviePagingUseCase: MoviePagingUseCase
): ViewModel() {
    val query = MutableStateFlow("")

    fun insertMovie(movie: Movie) = viewModelScope.launch {
        movieUseCase.insertMovie(movie)
    }

    val resultData = moviePagingUseCase.searchMoviesPaging(query)
        .cachedIn(viewModelScope)
        .asLiveData()
}