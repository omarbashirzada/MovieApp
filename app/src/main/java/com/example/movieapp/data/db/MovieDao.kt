package com.example.movieapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.data.model.FavouriteMovieModel
import com.example.movieapp.data.model.TrendingMoviesModel

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table ORDER BY vote_average DESC")
    fun getAllMovies(): LiveData<List<TrendingMoviesModel>>

    @Query("SELECT * FROM favorite_table ORDER BY vote_average DESC")
    fun getFavoriteMovies(): LiveData<List<FavouriteMovieModel>>

    @Query("DELETE FROM movie_table")
    suspend fun deleteMovieTable()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieList(movieModel: List<TrendingMoviesModel>)

    @Update
    suspend fun updateFavoriteStatus(movieModel: TrendingMoviesModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteMovie(favoriteMovieModel: FavouriteMovieModel)

    @Delete
    suspend fun removeFavoriteMovie(favoriteMovieModel: FavouriteMovieModel)

    @Query("SELECT * FROM favorite_table WHERE id=:id")
    suspend fun getFavoriteMovieById(id: Int): FavouriteMovieModel
}