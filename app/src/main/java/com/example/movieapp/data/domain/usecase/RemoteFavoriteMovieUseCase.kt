package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.FavouriteMovieModel


class RemoteFavoriteMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(favoriteMovieModel: FavouriteMovieModel) {
        movieRepository.removeFavoriteMovie(favoriteMovieModel)
    }
}