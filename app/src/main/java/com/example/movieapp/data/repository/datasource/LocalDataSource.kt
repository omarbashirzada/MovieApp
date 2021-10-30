package com.example.movieapp.data.repository.datasource

import androidx.lifecycle.LiveData
import com.example.movieapp.data.model.FavouriteMovieModel
import com.example.movieapp.data.model.TrendingMoviesModel

interface LocalDataSource {

    fun getAllMovies(): LiveData<List<TrendingMoviesModel>>
    fun getFavoriteMovies(): LiveData<List<FavouriteMovieModel>>
    suspend fun deleteMovieTable()
    suspend fun insertMovieList(movieModel: List<TrendingMoviesModel>)
    suspend fun updateFavoriteStatus(movieModel: TrendingMoviesModel)
    suspend fun insertFavoriteMovie(favoriteMovieModel: FavouriteMovieModel)
    suspend fun removeFavoriteMovie(favoriteMovieModel: FavouriteMovieModel)
    suspend fun getFavoriteMovieById(id: Int): FavouriteMovieModel
}