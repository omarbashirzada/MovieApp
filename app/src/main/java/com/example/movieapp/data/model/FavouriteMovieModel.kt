package com.example.movieapp.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movieapp.util.GenreConverter
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(tableName = "favorite_table")
data class FavouriteMovieModel(
    val backdrop_path: String? = null,
    val first_air_date: String? = null,
    @TypeConverters(GenreConverter::class)
    val genre_ids: List<Int>? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String? = null,
    val original_language: String? = null,
    val original_name: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val isFavorite: Boolean = false
) : Parcelable