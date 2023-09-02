package com.ranosan.wavies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("logos")
    val logos: List<ImageItem>
)
