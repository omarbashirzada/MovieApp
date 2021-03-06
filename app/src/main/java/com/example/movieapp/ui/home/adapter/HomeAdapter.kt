package com.example.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.model.TrendingMoviesModel
import com.example.movieapp.data.other.Constants
import com.example.movieapp.databinding.FilmItemLayoutBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.TrendViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<TrendingMoviesModel>() {
        override fun areItemsTheSame(
            oldItem: TrendingMoviesModel,
            newItem: TrendingMoviesModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TrendingMoviesModel,
            newItem: TrendingMoviesModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)


    inner class TrendViewHolder(
        private val trendingMoviesListBinding:FilmItemLayoutBinding ,
        setOnItemClickListener: ((TrendingMoviesModel) -> Unit)?,
        setOnFavoriteClickListener: ((TrendingMoviesModel) -> Unit)?
    ) :
        RecyclerView.ViewHolder(trendingMoviesListBinding.root) {

        fun bind(trendingMoviesModel: TrendingMoviesModel?) {
            trendingMoviesModel?.let { model ->
                trendingMoviesListBinding.apply {
                    if (model.title == null) {
                        movieTitle.text = model.name
                        if (model.name == null) {
                            movieTitle.text = model.original_title
                        }
                    } else {
                        movieTitle.text = model.title
                    }
                    if (model.isFavorite) {
                        iwFavorite.setImageResource(R.drawable.ic_favorite_movie)
                    } else {
                        iwFavorite.setImageResource(R.drawable.favourite)
                    }
                    movieImage.load(
                        Constants.IMAGE_URL +
                                differ.currentList.getOrNull(bindingAdapterPosition)?.poster_path
                    ) {
                        crossfade(true)
                    }
                    ratingBar.rating = model.vote_average?.toFloat() ?: 0f
                    ratingTV.text = model.vote_average?.toString()

                    root.setOnClickListener {
                        model.let { movies ->
                            setOnItemClick?.invoke(movies)
                        }
                    }
                    iwFavorite.setOnClickListener {
                        model.let { movies ->
                            setOnFavoriteClick?.invoke(movies)
                        }
                    }

                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val trendingMoviesListBinding =
            FilmItemLayoutBinding.inflate(layoutInflater, parent, false)
        return TrendViewHolder(
            trendingMoviesListBinding,
            setOnItemClick,
            setOnFavoriteClick
        )

    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bind(differ.currentList.getOrNull(position))
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var setOnItemClick: ((TrendingMoviesModel) -> Unit)? = null
    private var setOnFavoriteClick: ((TrendingMoviesModel) -> Unit)? = null

    fun setOnClickListener(listener: (TrendingMoviesModel) -> Unit) {
        setOnItemClick = listener
    }

    fun setOnFavoriteClickListener(listener: (TrendingMoviesModel) -> Unit) {
        setOnFavoriteClick = listener
    }


}