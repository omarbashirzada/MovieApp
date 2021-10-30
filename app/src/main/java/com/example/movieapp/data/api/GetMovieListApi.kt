package com.example.movieapp.data.api

import com.example.movieapp.data.model.TrendingMoviesRoot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMovieListApi{

    @GET("/3/trending/movie/week")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String): Response<TrendingMoviesRoot>

}