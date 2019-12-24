package com.example.adapt_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapt_app.R
import com.example.adapt_app.viewmodels.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val storiesAdapter = StoryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java) // instantiating viewmodel
        viewModel.refresh()

        storiesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = storiesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.stories.observe(this, Observer { stories ->
            stories?.let {
                storiesList.visibility = View.VISIBLE
                storiesAdapter.updateStories(it) }
        })

        viewModel.storyLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let { loading_view.visibility = if(it) View.VISIBLE else View.GONE
            if(it) {
                list_error.visibility = View.GONE
                storiesList.visibility = View.GONE
            }}
        })
    }
}
