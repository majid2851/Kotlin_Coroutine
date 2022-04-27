package com.example.kotlinmvvm2.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance
{
    //this is not singleton!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    companion object{
        val baseUrl="https://www.googleapis.com/books/v1/"//volumes?q=search

        fun getRetrofit() : Retrofit
        {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        }


    }



}