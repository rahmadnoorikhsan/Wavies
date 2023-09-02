package com.ranosan.wavies.core.data.source.remote

import com.ranosan.wavies.core.data.source.remote.retrofit.ApiResponse
import com.ranosan.wavies.core.data.source.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTrendingMovies() = flow {
        try {
            val response = apiService.getTrendingMovies()
            val data = response.results
            if (data.isNotEmpty()) emit(ApiResponse.Success(data))
            else emit(ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getPopularMovies() = flow {
        try {
            val response = apiService.getPopularMovies()
            val data = response.results
            if (data.isNotEmpty()) emit(ApiResponse.Success(data))
            else emit(ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getNowPlayingMovies() = flow {
        try {
            val response = apiService.getMoviesNowPlaying()
            val data = response.results
            if (data.isNotEmpty()) emit(ApiResponse.Success(data))
            else emit(ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getTopRatedMovies() = flow {
        try {
            val response = apiService.getTopRatedMovies()
            val data = response.results
            if (data.isNotEmpty()) emit(ApiResponse.Success(data))
            else emit(ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUpComingMovies() = flow {
        try {
            val response = apiService.getUpcomingMovies()
            val data = response.results
            if (data.isNotEmpty()) emit(ApiResponse.Success(data))
            else (emit(ApiResponse.Empty))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}