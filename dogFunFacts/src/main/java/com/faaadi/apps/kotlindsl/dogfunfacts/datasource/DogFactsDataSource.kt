package com.faaadi.apps.kotlindsl.dogfunfacts.datasource

import com.faaadi.apps.kotlindsl.dogfunfacts.models.DogFactsResModel
import retrofit2.Response


interface DogFactsDataSource {

    suspend fun getDogFunFacts(number: String): Response<ArrayList<DogFactsResModel>>

}