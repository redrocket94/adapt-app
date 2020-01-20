package com.example.adapt_app.views

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.example.adapt_app.R
import com.example.adapt_app.models.Article
import com.example.adapt_app.util.STORY_URL
import getProgressDrawable
import kotlinx.android.synthetic.main.item_story.view.*
import loadImage

class StoryListAdapter(
    var stories: ArrayList<Article>
) : RecyclerView.Adapter<StoryListAdapter.StoryViewHolder>() {

    fun updateStories(newStories: List<Article>) {
        stories.clear()
        stories.addAll(newStories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StoryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
    )

    override fun getItemCount() = stories.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = view.imageView
        private val storyName = view.name
        private val storyDescription = view.textContent
        private val progressDrawable = getProgressDrawable(view.context)
        private val storyView = view


        fun bind(story: Article) {
            storyName.text = story.title
            storyDescription.text = story.description
            imageView.loadImage(story.urlToImage, progressDrawable)

            storyView.setOnClickListener {
                val intent = Intent(
                    it.context,
                    WebViewActivity::class.java
                )

                intent.putExtra(STORY_URL, story.url)
                storyView.context.startActivity(intent)
            }
        }
    }
}