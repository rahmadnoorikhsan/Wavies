package com.ranosan.wavies.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ranosan.wavies.core.data.source.remote.response.MovieItem
import com.ranosan.wavies.core.data.source.remote.response.MovieResponse
import com.ranosan.wavies.core.data.source.remote.retrofit.ApiService
import com.ranosan.wavies.core.domain.model.MovieType

class MoviePagingSource(
    private val apiService: ApiService,
    private val movieType: MovieType,
    private val query: String? = null
) : PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> =
        try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val result: MovieResponse = when (movieType) {
                MovieType.NOW_PLAYING -> apiService.getMoviesNowPlaying(page = position)
                MovieType.TOP_RATED -> apiService.getTopRatedMovies(page = position)
                MovieType.POPULAR -> apiService.getPopularMovies(page = position)
                MovieType.UPCOMING -> apiService.getUpcomingMovies(page = position)
                MovieType.SEARCH -> apiService.searchMovies(query.toString(), page = position)
                MovieType.TRENDING -> apiService.getTrendingMovies()
            }
            LoadResult.Page(
                data = result.results,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (result.results.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}