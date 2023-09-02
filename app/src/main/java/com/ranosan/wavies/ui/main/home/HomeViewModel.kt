package com.ranosan.wavies.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ranosan.wavies.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieUseCase: MovieUseCase
): ViewModel() {

    val trendingMovies by lazy {
        movieUseCase.getTrendingMovies().asLiveData()
    }

    val popularMovies by lazy {
        movieUseCase.getPopularMovies().asLiveData()
    }

    val upComingMovies by lazy {
        movieUseCase.getUpcomingMovies().asLiveData()
    }

    val topRatedMovies by lazy {
        movieUseCase.getTopRatedMovies().asLiveData()
    }

    val nowPlayingMovies by lazy {
        movieUseCase.getMoviesNowPlaying().asLiveData()
    }
}