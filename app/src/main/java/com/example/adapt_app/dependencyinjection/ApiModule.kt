package com.example.adapt_app.dependencyinjection

import com.example.adapt_app.models.StoriesApi
import com.example.adapt_app.models.StoriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://newsapi.org"

    @Provides
    fun provideStoriesApi(): StoriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StoriesApi::class.java)
    }

    @Provides
    fun provideStoriesService(): StoriesService {
        return StoriesService()
    }
}