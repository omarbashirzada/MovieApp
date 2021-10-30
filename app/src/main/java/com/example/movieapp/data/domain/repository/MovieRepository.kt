package com.example.movieapp.data.domain.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.data.model.*
import com.example.movieapp.util.Resource

interface MovieRepository {

    suspend fun getTrendingMovies(): Resource<List<TrendingMoviesModel>>

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsModel>

    fun getAllMovies(): LiveData<List<TrendingMoviesModel>>

    fun getFavoriteMovies(): LiveData<List<FavouriteMovieModel>>

    suspend fun deleteMovieTable()

    suspend fun updateFavoriteStatus(movieModel: TrendingMoviesModel)

    suspend fun insertMovieList(movieModel: List<TrendingMoviesModel>)

    suspend fun getCredits(movieId: Int): Resource<List<Cast>>

    suspend fun getPersonData(personId: Int): Resource<ActorModel>

    suspend fun getMovieTrailer(movieId: Int): Resource<List<Result>>

    suspend fun insertFavoriteMovie(favoriteMovieModel: FavouriteMovieModel)

    suspend fun removeFavoriteMovie(favoriteMovieModel: FavouriteMovieModel)

    suspend fun getFavoriteMovieById(id: Int): FavouriteMovieModel


}