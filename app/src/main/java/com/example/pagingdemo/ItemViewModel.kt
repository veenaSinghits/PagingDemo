package com.example.pagingdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.paging.LivePagedListBuilder

class ItemViewModel: ViewModel() {
    //creating livedata for PagedList  and PagedKeyedDataSource
    val itemDataSourceFactory = ItemDataSourceFactory()

    //getting the live data source from data source factory
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Item>> = itemDataSourceFactory.getItemLiveDataSource()

    val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(ItemDataSource.PAGE_SIZE).build()
     var itemPagedList : LiveData<PagedList<Item>> = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
    .build()
//    lateinit var liveDataSource: LiveData<PageKeyedDataSource<Int, Item>>

//    fun ItemViewModel() {
//
//        //getting our data source factory
//        val itemDataSourceFactory = ItemDataSourceFactory()
//
//        //getting the live data source from data source factory
//        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()
//
//        //Getting PagedList config
//        val pagedListConfig = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setPageSize(ItemDataSource.PAGE_SIZE).build()
//
//        //Building the paged list
//        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
//            .build()
//    }
}