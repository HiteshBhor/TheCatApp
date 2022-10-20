package com.hpb.thecatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hpb.thecatapp.paging.CatPagingAdapter
import com.hpb.thecatapp.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var catViewModel: CatViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CatPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)

        catViewModel = ViewModelProvider(this)[CatViewModel::class.java]

        adapter = CatPagingAdapter()

        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        catViewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle, it)
        })

    }
}