package com.suret.moviesapp.domain.usecase

import com.example.movieapp.data.domain.repository.MovieRepository
import com.example.movieapp.data.model.ActorModel
import com.example.movieapp.util.Resource


class GetPersonDataUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(personId: Int): Resource<ActorModel> =
        movieRepository.getPersonData(personId)

}