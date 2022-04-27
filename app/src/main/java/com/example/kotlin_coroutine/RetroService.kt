package com.example.kotlinmvvm2.network

import com.example.kotlinmvvm2.network.model.BooksListModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService
{
    @GET("volumes")
    fun getBooks(@Query("q") title:String ):Observable<BooksListModel>


    @GET("volumes")
     fun getBooksRetrofit(@Query("q") title:String ): Call<BooksListModel>
}