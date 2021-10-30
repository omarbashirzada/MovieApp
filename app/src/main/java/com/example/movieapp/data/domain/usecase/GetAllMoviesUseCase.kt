package com.suret.moviesapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.TrendingMoviesModel


class GetAllMoviesUseCase(private val movieRepository: MovieRepository) {

    fun execute(): LiveData<List<TrendingMoviesModel>> =
        movieRepository.getAllMovies()
}