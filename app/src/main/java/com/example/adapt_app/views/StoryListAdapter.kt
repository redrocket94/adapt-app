package com.example.adapt_app.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapt_app.R
import com.example.adapt_app.models.Story
import com.example.adapt_app.util.getProgressDrawable
import com.example.adapt_app.util.loadImage
import kotlinx.android.synthetic.main.item_story.view.*

class StoryListAdapter(var stories: ArrayList<Story>): RecyclerView.Adapter<StoryListAdapter.StoryViewHolder>() {

    fun updateStories(newStories: List<Story>) {
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

    class StoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView = view.imageView
        private val storyName = view.name
        private val storyCapital = view.capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(story: Story) {
            storyName.text = story.title
            storyCapital.text = story.description
            imageView.loadImage(story.urlToImage, progressDrawable)
        }
    }
}