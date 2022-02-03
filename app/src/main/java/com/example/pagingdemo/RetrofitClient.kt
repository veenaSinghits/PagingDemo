package com.example.pagingdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://api.stackexchange.com/2.2/"
        private var mInstance = RetrofitClient()
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }

//        @Synchronized
//        fun getInstance(): Retrofit? {
//            if (mInstance == null) {
//                mInstance = RetrofitClient()
//            }
//            return mInstance
//        }


        fun getApi(): Api? {
            return retrofit?.create(Api::class.java)
        }
    }
}