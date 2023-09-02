package com.ranosan.wavies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<MovieItem>
)

