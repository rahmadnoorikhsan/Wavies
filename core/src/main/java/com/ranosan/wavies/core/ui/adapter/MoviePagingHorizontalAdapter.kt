package com.ranosan.wavies.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranosan.wavies.core.databinding.ItemMovieHorizontalBinding
import com.ranosan.wavies.core.domain.model.Movie
import com.ranosan.wavies.core.utils.Extentions.showImageInto
import com.ranosan.wavies.core.utils.Extentions.toAnotherDate

class MoviePagingHorizontalAdapter (
    private val data: (Movie, ImageView) -> Unit
) : PagingDataAdapter<Movie, MoviePagingHorizontalAdapter.MovieViewHolder>(MovieAdapter.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it) }
    }

    inner class MovieViewHolder(private val binding: ItemMovieHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                ivMovie.showImageInto(itemView.context, movie.posterPath)
                tvTitle.text = movie.title
                tvDate.text = movie.releaseDate?.toAnotherDate()
                itemView.setOnClickListener {
                    data.invoke(movie, ivMovie)
                }
            }
        }
    }
}