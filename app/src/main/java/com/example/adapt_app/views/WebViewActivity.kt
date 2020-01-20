package com.example.adapt_app.views

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.adapt_app.R
import com.example.adapt_app.util.STORY_URL

class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        println(1)
        super.onCreate(savedInstanceState)
        println(2)
        setContentView(R.layout.webview)

        val extra: Bundle? = intent.extras

        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return false
            }
        }
        webView.loadUrl(extra?.getString(STORY_URL))
    }
}