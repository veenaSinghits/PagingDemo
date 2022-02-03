package com.example.pagingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.paging.PagedList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setHasFixedSize(true)

        //getting our ItemViewModel
        var itemViewModel:ItemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        //creating the Adapter
        val adapter = ItemAdapter()
        //observing the itemPagedList from view model
        itemViewModel.itemPagedList.observe(this,Observer<PagedList<Item>> {
                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(it)
        })

        //setting the adapter
        recyclerView.adapter = adapter
    }
}