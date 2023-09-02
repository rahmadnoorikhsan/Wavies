package com.ranosan.wavies.core.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ranosan.wavies.core.BuildConfig
import com.ranosan.wavies.core.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Extentions {
    fun ImageView.showImageInto(context: Context, url: String?) {
        Glide.with(context)
            .load("${BuildConfig.BASE_URL_IMAGE}$url")
            .placeholder(ContextCompat.getDrawable(context, R.color.dark))
            .into(this)
    }

    fun ImageView.showThumbnailInto(context: Context, videoId: String?) {
        Glide.with(context)
            .load("https://i3.ytimg.com/vi/$videoId/maxresdefault.jpg")
            .into(this)
    }

    fun ChipGroup.addChips(context: Context, input: String?) {
        Chip(context).apply {
            id = View.generateViewId()
            text = input
            setChipBackgroundColorResource(R.color.dark)
            setTextColor(ResourcesCompat.getColor(context.resources, R.color.white, null))
            addView(this)
        }
    }

    fun String.toAnotherDate(): String =
        try {
            val df = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val date = df.parse(this) as Date
            SimpleDateFormat("yyyy", Locale.US).format(date)
        } catch (e: Exception) {
            "-"
        }
}