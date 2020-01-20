package com.example.adapt_app.models

import io.reactivex.Single
import retrofit2.http.GET

interface StoriesApi {

    @GET("v2/top-headlines?country=us&apiKey=5f49a386a37041a6b95d8902ce550103")
    fun getStories(): Single<Example>
}