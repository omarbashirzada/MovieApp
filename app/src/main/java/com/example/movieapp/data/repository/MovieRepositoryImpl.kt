package com.example.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.*
import com.example.movieapp.util.Resource
import com.example.movieapp.data.repository.datasource.LocalDataSource
import com.example.movieapp.data.repository.datasource.RemoteDataSource

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getTrendingMovies(): Resource<List<TrendingMoviesModel>> {
        val response = remoteDataSource.getTrendingMovies(BuildConfig.API_KEY)
        val result = response.body()
        result?.let {
            return if (response.isSuccessful) {
                Resource.Success(it.results!!)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())

    }

    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsModel> {
        val response = remoteDataSource.getMovieDetails(movieId, BuildConfig.API_KEY)
        val result = response.body()
        result?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override fun getAllMovies(): LiveData<List<TrendingMoviesModel>> =
        localDataSource.getAllMovies()

    override fun getFavoriteMovies(): LiveData<List<FavouriteMovieModel>> =
        localDataSource.getFavoriteMovies()

    override suspend fun deleteMovieTable() {
        localDataSource.deleteMovieTable()
    }

    override suspend fun updateFavoriteStatus(movieModel: TrendingMoviesModel) {
        localDataSource.updateFavoriteStatus(movieModel)
    }


    override suspend fun insertMovieList(movieModel: List<TrendingMoviesModel>) {
        localDataSource.insertMovieList(movieModel)
    }

    override suspend fun getCredits(movieId: Int): Resource<List<Cast>> {
        val response = remoteDataSource.getCredits(movieId, BuildConfig.API_KEY)
        val result = response.body()
        result?.let {
            return if (response.isSuccessful) {
                Resource.Success(it.cast!!)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getPersonData(personId: Int): Resource<ActorModel> {
        val response = remoteDataSource.getPersonData(personId, BuildConfig.API_KEY)
        val result = response.body()
        result?.let {
            return if (response.isSuccessful) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getMovieTrailer(movieId: Int): Resource<List<Result>> {
        val response = remoteDataSource.getMovieTrailer(movieId, BuildConfig.API_KEY)
        val result = response.body()
        result?.let {
            return if (response.isSuccessful) {
                Resource.Success(it.results!!)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun insertFavoriteMovie(favoriteMovieModel: FavouriteMovieModel) {
        localDataSource.insertFavoriteMovie(favoriteMovieModel)
    }

    override suspend fun removeFavoriteMovie(favoriteMovieModel: FavouriteMovieModel) {
        localDataSource.removeFavoriteMovie(favoriteMovieModel)
    }

    override suspend fun getFavoriteMovieById(id: Int): FavouriteMovieModel {
        return localDataSource.getFavoriteMovieById(id)
    }
}