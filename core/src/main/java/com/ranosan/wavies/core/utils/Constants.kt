package com.ranosan.wavies.core.utils

import com.ranosan.wavies.core.BuildConfig

object Constants {
    private const val BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"

    fun getBearer(): String {
        return BEARER + BuildConfig.API_KEY
    }
}