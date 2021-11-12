package com.example.newsapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.adapter.ThumbnailAdapter
import com.example.newsapi.data.ArticlesItem
import com.example.newsapi.databinding.ActivityMainBinding
import com.example.newsapi.view.DetailActivity
import com.example.newsapi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), ThumbnailAdapter.OnItemClickCallback {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private lateinit var headlineAdapter: ThumbnailAdapter
    private lateinit var businessAdapter: ThumbnailAdapter
    private lateinit var techAdapter: ThumbnailAdapter
    private lateinit var entertainmentAdapter: ThumbnailAdapter
    private lateinit var generalAdapter: ThumbnailAdapter
    private lateinit var healthAdapter: ThumbnailAdapter
    private lateinit var sportsAdapter: ThumbnailAdapter
    private lateinit var scienceAdapter: ThumbnailAdapter

    private var data: MutableList<ArticlesItem?> = ArrayList()
    private var page = 1
    private var nextPage = true
    private var isEmpty = true
    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setViewModel()
        showThumbnail()
        onClick()


    }

//    private fun getData() {
//        getLoading()
//
//
//    }

    private fun onClick() {
        binding.btnBisnis.setOnClickListener {
            businessAdapter = ThumbnailAdapter(ArrayList(), this)
            binding.rvMain.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = businessAdapter
            }
            mainViewModel.getCategory(page, "business").observe(this, {
                businessAdapter.setData(it)
                Log.e("bisnisData", "$it")
            })
        }

        binding.btnEntertainment.setOnClickListener {
            entertainmentAdapter = ThumbnailAdapter(ArrayList(), this)
            binding.rvMain.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = entertainmentAdapter
            }
            mainViewModel.getCategory(page, "entertainment").observe(this, {
                entertainmentAdapter.setData(it)
                Log.e("entertainmentData", "$it" )
            })
        }

        binding.btnTech.setOnClickListener {
            techAdapter = ThumbnailAdapter(ArrayList(), this)
            binding.rvMain.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = techAdapter
            }
            mainViewModel.getCategory(page, "technology").observe(this, {
                techAdapter.setData(it)
                Log.e("techData", "$it")
            })
        }

        binding.btnGeneral.setOnClickListener {
            generalAdapter = ThumbnailAdapter(ArrayList(), this)
            binding.rvMain.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = generalAdapter
            }
            mainViewModel.getCategory(page, "general").observe(this, {
                generalAdapter.setData(it)
                Log.e("generalData", "$it" )
            })
        }

        binding.btnHealt.setOnClickListener {
            healthAdapter = ThumbnailAdapter(ArrayList(), this)
            binding.rvMain.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = healthAdapter
            }
            mainViewModel.getCategory(page, "health").observe(this, {
                healthAdapter.setData(it)
                Log.e("healthData", "$it" )
            })
        }

        binding.btnSport.setOnClickListener {
            sportsAdapter = ThumbnailAdapter(ArrayList(), this)
            binding.rvMain.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = sportsAdapter
                Log.e("sportData", "$it" )
            }
            mainViewModel.getCategory(page, "sports").observe(this, {
                sportsAdapter.setData(it)
                Log.e("sportData", "$it" )
            })
        }

        binding.btnScience.setOnClickListener {
            scienceAdapter = ThumbnailAdapter(ArrayList(), this)
            binding.rvMain.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = scienceAdapter
                Log.e("scienceData", "$it" )
            }
            mainViewModel.getCategory(page, "science").observe(this, {
                scienceAdapter.setData(it)
                Log.e("scienceData", "$it" )
            })
        }

    }

    private fun setAdapter() {
        headlineAdapter = ThumbnailAdapter(ArrayList(), this)
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = headlineAdapter
        }

    }

    private fun setViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun showThumbnail() {
        mainViewModel.getHeadlines(page).observe(this, {
            headlineAdapter.setData(it)
            Log.e("headlineData", "$it")
        })

    }

//    private fun getLoading() {
//        mainViewModel.getLoading().observe(this, {
//            loading = it
//            if (loading) binding.progressCircular.visibility = VISIBLE
//            else binding.progressCircular.visibility = GONE
//        })
//
//        mainViewModel.getStatus().observe(this, {
//            if (it >= 400) binding.lineNodata.visibility = GONE
//        })
//
//        mainViewModel.getMessage().observe(this, {
//            if (!it.isNullOrEmpty()) binding.lineNodata.visibility = VISIBLE
//            binding.rvMain.visibility = GONE
//
//            if (it.isNullOrEmpty()) binding.lineNodata.visibility = GONE
//
//        })
//
//        binding.rvMain.addOnScrollListener(object: RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val countItem = linearLayoutManager.itemCount
//                if (countItem.minus(1) == linearLayoutManager.findLastVisibleItemPosition()) {
//                    if (!loading && nextPage) {
//                        page++
//                        getData()
//                    }
//                }
//            }
//        })
//
//    }

    override fun onItemClick(data: ArticlesItem) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra(DetailActivity.IMAGE, data.urlToImage)
                .putExtra(DetailActivity.TITLE, data.title)
                .putExtra(DetailActivity.PUBLISHED, data.publishedAt)
                .putExtra(DetailActivity.AUTHOR, data.author)
                .putExtra(DetailActivity.CONTENT, data.content.toString())
        )
    }

}