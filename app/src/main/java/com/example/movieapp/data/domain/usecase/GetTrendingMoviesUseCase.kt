package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.TrendingMoviesModel
import com.example.movieapp.util.Resource


class GetTrendingMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(): Resource<List<TrendingMoviesModel>> = movieRepository.getTrendingMovies()
}