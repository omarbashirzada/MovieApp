package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.FavouriteMovieModel


class GetFavoriteMovieByIdUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(id: Int): FavouriteMovieModel = movieRepository.getFavoriteMovieById(id)
}