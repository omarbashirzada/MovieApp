package com.example.movieapp.data.repository.datasourceimpl

import androidx.lifecycle.LiveData
import com.example.movieapp.data.db.MovieDao
import com.example.movieapp.data.model.FavouriteMovieModel
import com.example.movieapp.data.model.TrendingMoviesModel
import com.example.movieapp.data.repository.datasource.LocalDataSource

class LocalDataSourceImpl (private val movieDao: MovieDao) : LocalDataSource {

    override fun getAllMovies(): LiveData<List<TrendingMoviesModel>> =
        movieDao.getAllMovies()


    override fun getFavoriteMovies(): LiveData<List<FavouriteMovieModel>> =
        movieDao.getFavoriteMovies()

    override suspend fun deleteMovieTable() {
        movieDao.deleteMovieTable()
    }

    override suspend fun insertMovieList(movieModel: List<TrendingMoviesModel>) {
        movieDao.insertMovieList(movieModel)
    }

    override suspend fun updateFavoriteStatus(movieModel: TrendingMoviesModel) {
        movieDao.updateFavoriteStatus(movieModel)
    }

    override suspend fun insertFavoriteMovie(favoriteMovieModel: FavouriteMovieModel) {
        movieDao.insertFavoriteMovie(favoriteMovieModel)
    }

    override suspend fun removeFavoriteMovie(favoriteMovieModel: FavouriteMovieModel) {
        movieDao.removeFavoriteMovie(favoriteMovieModel)
    }

    override suspend fun getFavoriteMovieById(id: Int): FavouriteMovieModel =
        movieDao.getFavoriteMovieById(id)


}