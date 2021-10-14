package com.faaadi.apps.kotlindsl.dogfunfacts.datasource

import com.faaadi.apps.kotlindsl.dogfunfacts.apis.DogFactsService
import com.faaadi.apps.kotlindsl.dogfunfacts.models.DogFactsResModel
import retrofit2.Response
import javax.inject.Inject


class DogFunFactsDataSourceImpl @Inject constructor(private val dogFactsService: DogFactsService) :
    DogFactsDataSource {

    override suspend fun getDogFunFacts(number: String): Response<ArrayList<DogFactsResModel>> =
        dogFactsService.getDogFunFacts(number)


}