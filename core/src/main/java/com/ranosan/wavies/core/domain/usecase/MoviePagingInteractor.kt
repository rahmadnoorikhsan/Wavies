package com.ranosan.wavies.core.domain.usecase

import com.ranosan.wavies.core.domain.model.MovieType
import com.ranosan.wavies.core.domain.repository.MoviePagingRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MoviePagingInteractor @Inject constructor(
    private val moviePagingRepository: MoviePagingRepository
) : MoviePagingUseCase {
    override fun getMoviesPaging(movieType: MovieType) = moviePagingRepository.getMoviesPaging(movieType)

    override fun searchMoviesPaging(query: StateFlow<String>) = moviePagingRepository.searchMovie(query)
}