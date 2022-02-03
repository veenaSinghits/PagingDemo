package com.example.pagingdemo

import androidx.paging.DataSource
import androidx.lifecycle.MutableLiveData

import androidx.paging.PageKeyedDataSource





class ItemDataSourceFactory : DataSource.Factory<Int, Item>() {

    //creating the mutable live data
    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Item>>()
    override fun create(): DataSource<Int, Item> {
        //getting our data source object
        val itemDataSource = ItemDataSource()

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource)

        //returning the datasource
        return itemDataSource
    }

    //getter for itemlivedatasource
    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Item>> {
        return itemLiveDataSource
    }
}