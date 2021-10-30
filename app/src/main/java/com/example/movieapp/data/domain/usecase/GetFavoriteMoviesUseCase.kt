package com.suret.moviesapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.FavouriteMovieModel


class GetFavoriteMoviesUseCase(private val movieRepository: MovieRepository) {

    fun execute(): LiveData<List<FavouriteMovieModel>> =
        movieRepository.getFavoriteMovies()
}