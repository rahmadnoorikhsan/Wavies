package com.ranosan.wavies.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranosan.wavies.core.databinding.ItemImageSliderBinding
import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.ui.adapter.MovieAdapter.Companion.DIFF_CALLBACK
import com.ranosan.wavies.core.utils.DataMapper
import com.ranosan.wavies.core.utils.Extentions.addChips
import com.ranosan.wavies.core.utils.Extentions.showImageInto

class SliderAdapter(val data: (Movie, ImageView) -> Unit) : ListAdapter<Movie, SliderAdapter.SliderViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = ItemImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class SliderViewHolder(private val binding: ItemImageSliderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                itemView.setOnClickListener { data.invoke(movie, ivSlider) }
                ivSlider.showImageInto(itemView.context, movie.posterPath)
                cgGenres.removeAllViews()
                DataMapper.mapGenreIdToGenre(movie.genreIds)
                    .filterNotNull()
                    .take(3)
                    .forEach {cgGenres.addChips(itemView.context, it)}
            }
        }
    }
}