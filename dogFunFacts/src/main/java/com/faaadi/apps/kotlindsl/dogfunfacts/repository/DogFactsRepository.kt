package com.faaadi.apps.kotlindsl.dogfunfacts.repository

import com.faaadi.apps.kotlindsl.dogfunfacts.datasource.DogFactsDataSource
import com.faaadi.apps.kotlindsl.dogfunfacts.models.DogFactsResModel
import com.faaadi.apps.networkcore.handlers.ResourceState
import com.faaadi.apps.networkcore.utils.NetworkBoundSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


class DogFactsRepository(
    private val dogFactsDataSource: DogFactsDataSource
) {

    fun getDogFunFacts(number: String): Flow<ResourceState<ArrayList<DogFactsResModel>>> {
        return object : NetworkBoundSource<ArrayList<DogFactsResModel>, ArrayList<DogFactsResModel>>() {

            override suspend fun fetchFromRemote(): Response<ArrayList<DogFactsResModel>> {

                return dogFactsDataSource.getDogFunFacts(number)
            }

            override suspend fun postProcess(originalData: ArrayList<DogFactsResModel>): ArrayList<DogFactsResModel> {
                return originalData
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }
}
