package com.faaadi.apps.kotlindsl.dogfunfacts.di

import android.content.Context
import com.faaadi.apps.kotlindsl.dogfunfacts.apis.DogFactsService
import com.faaadi.apps.kotlindsl.dogfunfacts.datasource.DogFactsDataSource
import com.faaadi.apps.kotlindsl.dogfunfacts.datasource.DogFunFactsDataSourceImpl
import com.faaadi.apps.kotlindsl.dogfunfacts.repository.DogFactsRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


const val CAT_RETROFIT_NAME = "catNetworkInterface"
const val DOG_RETROFIT_NAME = "dogNetworkInterface"

@Module
@InstallIn(SingletonComponent::class)
class DogFactsModule {

    val dogApiBaseUrl = "https://dog-facts-api.herokuapp.com"
    val catApiBaseUrl = "https://cat-fact.herokuapp.com"

    @Singleton
    @Provides
    fun providesContext(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    @Named(DOG_RETROFIT_NAME)
    fun providesDogFactsRetrofit(
        @ApplicationContext context: Context
    ): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder().baseUrl(dogApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    @Named(CAT_RETROFIT_NAME)
    fun provideCatFactsRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder().baseUrl(catApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }


    @Singleton
    @Provides
    fun provideDogsFactsService(@Named(DOG_RETROFIT_NAME) retrofit: Retrofit): DogFactsService {
        return retrofit.create(DogFactsService::class.java)
    }

    @Singleton
    @Provides
    fun providesDogFactsDataSource(
        dogFactsService: DogFactsService
    ): DogFactsDataSource = DogFunFactsDataSourceImpl(dogFactsService)


    @Singleton
    @Provides
    fun provideDogFactsRepository(
        dogsFactsDataSource: DogFactsDataSource
    ): DogFactsRepository = DogFactsRepository(dogsFactsDataSource)


}


