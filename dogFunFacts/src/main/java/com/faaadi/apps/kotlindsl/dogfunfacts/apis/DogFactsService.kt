package com.faaadi.apps.kotlindsl.dogfunfacts.apis

import com.faaadi.apps.kotlindsl.dogfunfacts.models.DogFactsResModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface DogFactsService {

    @GET("api/v1/resources/dogs")
    suspend fun getDogFunFacts(@Query("number") number: String): Response<ArrayList<DogFactsResModel>>


}