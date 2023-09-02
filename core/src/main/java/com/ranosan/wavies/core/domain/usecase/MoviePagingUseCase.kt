package com.ranosan.wavies.core.domain.usecase

import androidx.paging.PagingData
import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.domain.model.MovieType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MoviePagingUseCase {
    fun getMoviesPaging(movieType: MovieType): Flow<PagingData<Movie>>

    fun searchMoviesPaging(query: StateFlow<String>): Flow<PagingData<Movie>>
}