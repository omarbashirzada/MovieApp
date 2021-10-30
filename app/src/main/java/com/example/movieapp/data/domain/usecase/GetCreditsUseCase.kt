package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.Cast
import com.example.movieapp.util.Resource


class GetCreditsUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(id: Int): Resource<List<Cast>> = movieRepository.getCredits(id)
}