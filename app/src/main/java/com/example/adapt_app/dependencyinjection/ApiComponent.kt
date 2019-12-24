package com.example.adapt_app.dependencyinjection

import com.example.adapt_app.models.StoriesService
import com.example.adapt_app.viewmodels.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: StoriesService)

    fun inject(viewModel: ListViewModel)
}