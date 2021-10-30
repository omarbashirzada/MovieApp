package com.example.movieapp.data.api

import com.example.movieapp.data.model.CreditsModelRoot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetCreditsApi {


    @GET("/3/movie/{id}/credits")
    suspend fun getCredits(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<CreditsModelRoot>

}