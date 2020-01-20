package com.example.adapt_app.util

import com.example.adapt_app.models.Article
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type


internal class MyDeserializer : JsonDeserializer<Article> {
    @Throws(JsonParseException::class)
    override fun deserialize(je: JsonElement, type: Type, jdc: JsonDeserializationContext): Article {
        // Get the "content" element from the parsed JSON
        val content = je.asJsonObject.get("articles")

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson<Article>(content, Article::class.java!!)

    }
}