package com.ranosan.wavies.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ranosan.wavies.core.data.paging.MoviePagingSource
import com.ranosan.wavies.core.data.source.remote.retrofit.ApiService
import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.domain.model.MovieType
import com.ranosan.wavies.core.domain.repository.MoviePagingRepository
import com.ranosan.wavies.core.utils.DataMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MoviePagingRepository {
    override fun getMoviesPaging(movieType: MovieType): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = 8),
            pagingSourceFactory = {
                MoviePagingSource(apiService, movieType)
            }
        ).flow.map {
            DataMapper.mapPagingResponseToPagingDomain(it, movieType)
        }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override fun searchMovie(query: StateFlow<String>): Flow<PagingData<Movie>> =
        query.debounce(300)
            .filter { it.trim().isNotEmpty() }
            .distinctUntilChanged()
            .flatMapLatest {
                Pager(
                    config = PagingConfig(pageSize = 10),
                    pagingSourceFactory = {
                        MoviePagingSource(apiService, MovieType.SEARCH, it)
                    }
                ).flow.map {
                    DataMapper.mapPagingResponseToPagingDomain(it, MovieType.SEARCH)
                }
            }
}