package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.TrendingMoviesModel


class UpdateFavoriteStatusUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movieModel: TrendingMoviesModel) {
        movieRepository.updateFavoriteStatus(movieModel)
    }
}