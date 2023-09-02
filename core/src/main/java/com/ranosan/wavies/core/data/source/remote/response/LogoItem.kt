package com.ranosan.wavies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ImageItem(

    @field:SerializedName("file_path")
    val filePath: String? = null,
)