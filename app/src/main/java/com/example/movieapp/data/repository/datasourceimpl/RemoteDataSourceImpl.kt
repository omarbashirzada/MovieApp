package com.example.movieapp.data.repository.datasourceimpl

import com.example.movieapp.data.api.*
import com.example.movieapp.data.model.*
import com.example.movieapp.data.repository.datasource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl (
    private val getTrendingMoviesAPI: GetMovieListApi,
    private val getCreditsAPI: GetCreditsApi,
    private val getMovieTrailerAPI: GetMovieTrailerApi,
    private val getPersonDataAPI: GetPersonDataApi,
    private val getMovieDetailsAPI: GetMovieDetailsApi
) : RemoteDataSource {

    override suspend fun getTrendingMovies(apiKey: String): Response<TrendingMoviesRoot> =
        getTrendingMoviesAPI.getTrendingMovies(apiKey)

    override suspend fun getCredits(id: Int, apiKey: String): Response<CreditsModelRoot> =
        getCreditsAPI.getCredits(id, apiKey)

    override suspend fun getPersonData(id: Int, apiKey: String): Response<ActorModel> =
        getPersonDataAPI.getPersonData(id, apiKey)

    override suspend fun getMovieTrailer(id: Int, apiKey: String): Response<TrailerModelRoot> =
        getMovieTrailerAPI.getMovieTrailer(id, apiKey)

    override suspend fun getMovieDetails(id: Int, apiKey: String): Response<MovieDetailsModel> =
        getMovieDetailsAPI.getMovieDetails(id, apiKey)


}