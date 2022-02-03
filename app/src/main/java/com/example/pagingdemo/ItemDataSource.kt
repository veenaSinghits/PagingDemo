package com.example.pagingdemo

import android.view.Display
import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback;
import retrofit2.Response


class ItemDataSource : PageKeyedDataSource<Int, Item>() {
 companion object {
     //the size of a page that we want
     val PAGE_SIZE = 5

     //we will start from the first page which is 1
     private val FIRST_PAGE = 3

     //we need to fetch from stackoverflow
     private val SITE_NAME = "stackoverflow"
 }
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        RetrofitClient.getClient()?.create(Api::class.java)
            ?.getAnswers(FIRST_PAGE, PAGE_SIZE,SITE_NAME)
            ?.enqueue(object : Callback<Model?> {
                override fun onResponse(
                    call: Call<Model?>?,
                    response: Response<Model?>
                ) {
                    if (response.body() != null) {
                        response.body()!!.items?.let { callback.onResult(it, null, FIRST_PAGE + 1) }
                    }
                }

                override fun onFailure(call: Call<Model?>?, t: Throwable?) {}
            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        RetrofitClient.getClient()?.create(Api::class.java)?.getAnswers(params.key, PAGE_SIZE,SITE_NAME)
            ?.enqueue(object : Callback<Model?> {
                override fun onResponse(
                    call: Call<Model?>,
                    response: Response<Model?>
                ) {

                    //if the current page is greater than one
                    //we are decrementing the page number
                    //else there is no previous page
                    val adjacentKey = if (params.key > 1) params.key - 1 else null
                    if (response.body() != null) {

                        //passing the loaded data
                        //and the previous page key
                        response.body()!!.items?.let { callback.onResult(it, adjacentKey) }
                    }
                }

                override fun onFailure(call: Call<Model?>, t: Throwable) {}
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        RetrofitClient.getClient()?.create(Api::class.java)
            ?.getAnswers(params.key, PAGE_SIZE, SITE_NAME)
            ?.enqueue(object : Callback<Model?> {
                override fun onResponse(
                    call: Call<Model?>,
                    response: Response<Model?>
                ) {
                    if (response.body() != null) {
                        //if the response has next page
                        //incrementing the next page number
                        val key = if (response.body()!!.has_more) params.key + 1 else null

                        //passing the loaded data and next page value
                        response.body()!!.items?.let { callback.onResult(it, key) }
                    }
                }

                override fun onFailure(call: Call<Model?>, t: Throwable) {}
            })
    }
}