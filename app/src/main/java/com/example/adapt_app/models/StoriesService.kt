package com.example.adapt_app.models

import com.example.adapt_app.dependencyinjection.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class StoriesService {

    @Inject
    lateinit var api: StoriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getStories(): Single<List<Story>> {
        return api.getStories()
    }
}