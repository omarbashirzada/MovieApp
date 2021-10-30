package com.example.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.model.FavouriteMovieModel
import com.example.movieapp.data.model.TrendingMoviesModel
import com.example.movieapp.util.GenreConverter

@Database(
    entities = [TrendingMoviesModel::class, FavouriteMovieModel::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(GenreConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}