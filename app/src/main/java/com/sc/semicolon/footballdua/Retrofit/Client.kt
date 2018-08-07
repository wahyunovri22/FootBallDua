package com.sc.semicolon.footballdua.Retrofit

import com.google.gson.GsonBuilder
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

/**
 * Created by cis on 01/08/2018.
 */
object Client {

    private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    fun provideApi(java: Class<Service>): Service {
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
                .create(Service::class.java)
    }
}