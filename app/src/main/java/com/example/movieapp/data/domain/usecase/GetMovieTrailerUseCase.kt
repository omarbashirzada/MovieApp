package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import com.example.movieapp.data.model.Result


class GetMovieTrailerUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movieId: Int): Resource<List<Result>> =
        movieRepository.getMovieTrailer(movieId)

}