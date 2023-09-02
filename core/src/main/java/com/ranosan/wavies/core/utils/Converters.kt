package com.ranosan.wavies.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun listIntToString(list: List<Int?>): String =
        Gson().toJson(list)

    @TypeConverter
    fun stringToListInt(data: String): List<Int?> =
        Gson().fromJson(
            data,
            object : TypeToken<List<Int?>>() {}.type
        )
}