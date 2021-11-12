package com.example.newsapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import coil.load
import com.example.newsapi.R
import com.example.newsapi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val IMAGE = "image"
        const val AUTHOR = "author"
        const val TITLE = "title"
        const val PUBLISHED = "published"
        const val CONTENT = "content"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img = intent.getStringExtra(IMAGE)
        val title = intent.getStringExtra(TITLE)
        val author = intent.getStringExtra(AUTHOR)
        val published = intent.getStringExtra(PUBLISHED)
        val content = intent.getStringExtra(CONTENT)

        binding.imgDetail.load(img) {
            crossfade(true)
            crossfade(1000)
        }

        binding.txtTitle.text = title
        binding.txtAuthor.text = author
        binding.txtPublished.text = published
        Log.e("publishedData", "$published" )
        binding.txtContent.text = content
        Log.e("contentData", "$content" )

    }
}