package com.example.adapt_app.models

import io.reactivex.Single
import retrofit2.http.GET

interface StoriesApi {

    @GET("v2/top-headlines?country=us&apiKey=###") // Insert your own API key here
    fun getStories(): Single<Response>
}