package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.MovieDetailsModel
import com.example.movieapp.util.Resource


class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(movieId: Int): Resource<MovieDetailsModel> =
        movieRepository.getMovieDetails(movieId)
}