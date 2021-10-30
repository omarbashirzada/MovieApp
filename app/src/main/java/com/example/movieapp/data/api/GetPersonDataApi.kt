package com.example.movieapp.data.api

import com.example.movieapp.data.model.ActorModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetPersonDataApi {

    @GET("/3/person/{personId}")
    suspend fun getPersonData(
        @Path("personId") id: Int,
        @Query("api_key") apiKey: String
    ): Response<ActorModel>
}