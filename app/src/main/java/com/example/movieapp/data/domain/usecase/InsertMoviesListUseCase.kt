package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.TrendingMoviesModel


class InsertMoviesListUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movieModel: List<TrendingMoviesModel>) {
        movieRepository.insertMovieList(movieModel)
    }
}