package com.example.movieapp.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.data.model.FavouriteMovieModel
import com.example.movieapp.databinding.FavoriteListLayoutBinding
import com.example.movieapp.data.other.Constants

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavoriteViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<FavouriteMovieModel>() {
        override fun areItemsTheSame(
            oldItem: FavouriteMovieModel,
            newItem: FavouriteMovieModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavouriteMovieModel,
            newItem: FavouriteMovieModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)


    inner class FavoriteViewHolder(
        private val favoriteListLayoutBinding: FavoriteListLayoutBinding,
        setOnItemClickListener: ((FavouriteMovieModel) -> Unit)?,
        setOnFavoriteClickListener: ((FavouriteMovieModel) -> Unit)?
    ) :
        RecyclerView.ViewHolder(favoriteListLayoutBinding.root) {
        fun bind(favoriteMovieModel: FavouriteMovieModel?) {
            favoriteListLayoutBinding.apply {
                favoriteMovieModel?.let { model ->
                    if (model.title == null) {
                        movieTitleFavourite.text = model.name
                        if (model.name == null) {
                            movieTitleFavourite.text = model.original_title
                        }
                    } else {
                        movieTitleFavourite.text = model.title
                    }
                    movieImageFavourite.load(
                        Constants.IMAGE_URL +
                                differ.currentList.getOrNull(bindingAdapterPosition)?.backdrop_path
                    ) {
                        crossfade(true)
                    }
                    ratingTVFavourite.text = model.vote_average.toString()

                    root.setOnClickListener {
                        model.let { fav ->
                            setOnItemClick?.invoke(fav)
                        }
                    }
                    iwFavorite.setOnClickListener {
                        model.let { fav ->
                            setOnFavoriteClick?.invoke(fav)
                        }
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            FavoriteListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), setOnItemClick, setOnFavoriteClick
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(differ.currentList.getOrNull(position))
    }

    private var setOnItemClick: ((FavouriteMovieModel) -> Unit)? = null
    private var setOnFavoriteClick: ((FavouriteMovieModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (FavouriteMovieModel) -> Unit) {
        setOnItemClick = listener
    }

    fun setOnFavoriteClickListener(listener: (FavouriteMovieModel) -> Unit) {
        setOnFavoriteClick = listener
    }

    override fun getItemCount(): Int = differ.currentList.size

}