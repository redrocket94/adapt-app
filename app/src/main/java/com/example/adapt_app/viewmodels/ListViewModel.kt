package com.example.adapt_app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adapt_app.dependencyinjection.DaggerApiComponent
import com.example.adapt_app.models.StoriesService
import com.example.adapt_app.models.Article
import com.example.adapt_app.models.Example
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.reflect.typeOf

class ListViewModel: ViewModel() {

    @Inject
    lateinit var storiesService: StoriesService

    init {
        DaggerApiComponent.create().inject(this)
    }
    private val disposable = CompositeDisposable()

    val stories = MutableLiveData<List<Article>>()
    val storyLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchStories()
    }

    private fun fetchStories() {
        loading.value = true
        disposable.add(
            storiesService.getStories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Example>() {
                    override fun onSuccess(value: Example?) {
                        stories.value = value?.articles
                        storyLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        storyLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        disposable.clear()
    }
}