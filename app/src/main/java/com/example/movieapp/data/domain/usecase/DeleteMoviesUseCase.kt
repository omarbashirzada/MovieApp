package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository


class DeleteMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute() {
        movieRepository.deleteMovieTable()
    }
}