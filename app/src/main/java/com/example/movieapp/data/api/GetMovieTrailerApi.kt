package com.example.movieapp.data.api

import com.example.movieapp.data.model.TrailerModelRoot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieTrailerApi {

    @GET("/3/movie/{id}/videos")
    suspend fun getMovieTrailer(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<TrailerModelRoot>

}